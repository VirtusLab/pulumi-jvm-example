package org.example;

import io.pulumi.Config;
import io.pulumi.Stack;
import io.pulumi.core.Output;
import io.pulumi.core.internal.annotations.OutputExport;
import io.pulumi.kubernetes.Provider;
import io.pulumi.kubernetes.core.v1.Namespace;
import io.pulumi.kubernetes.core.v1.Secret;
import io.pulumi.kubernetes.core.v1.inputs.NamespaceArgs;
import io.pulumi.kubernetes.core.v1.inputs.SecretArgs;
import io.pulumi.kubernetes.inputs.ProviderArgs;
import io.pulumi.kubernetes.meta.v1.inputs.ObjectMetaArgs;
import io.pulumi.kubernetes.meta.v1.outputs.ObjectMeta;
import io.pulumi.resources.CustomResourceOptions;
import pl.pawelprazak.pulumi.kind.Cluster;
import pl.pawelprazak.pulumi.kind.inputs.ClusterArgs;
import pl.pawelprazak.pulumi.kind.inputs.ClusterKindConfigArgs;
import pl.pawelprazak.pulumi.kind.inputs.ClusterKindConfigNodeArgs;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class MyStack extends Stack {

    @OutputExport(type = String.class)
    public final Output<String> clusterName;

    @OutputExport(type = String.class)
    public final Output<String> theNamespace;

    @OutputExport(type = String.class)
    public final Output<String> theSecret;

    @OutputExport(type = Map.class, parameters = {String.class, String.class})
    public final Output<Map<String, String>> theSecretData;

    public MyStack() {
        var config = Config.of("example-jvm");
        var name = config.require("name");

        var cluster = new Cluster(name, ClusterArgs.builder()
                .setKindConfig(ClusterKindConfigArgs.builder()
                        .setKind("Cluster")
                        .setApiVersion("kind.x-k8s.io/v1alpha4")
                        .setNodes(List.of(
                                ClusterKindConfigNodeArgs.builder().setRole("control-plane").build(),
                                ClusterKindConfigNodeArgs.builder().setRole("worker").build()
                        ))
                        .build())
                .setWaitForReady(true)
                .build(),
                null
        );

        this.clusterName = cluster.getName();
        var k8sProvider = new Provider("k8sprovider", ProviderArgs.builder()
                .setKubeconfig(cluster.getKubeconfig().toInput())
                .build(),
                null
        );

        var ns = new Namespace(name, NamespaceArgs.builder()
                .setMetadata(
                        ObjectMetaArgs.builder()
                                .setName(name)
                                .setLabels(Map.of("app", name))
                                .build()
                ).build(),
                CustomResourceOptions.builder()
                        .setDependsOn(List.of(cluster))
                        .setProvider(k8sProvider)
                        .build()
        );

        this.theNamespace = Output.allOutputs(
                ns.getApiVersion(),
                ns.getKind(),
                ns.getMetadata().applyOptional(ObjectMeta::getName)
        ).applyValue(ss -> String.join("/", ss));

        var secret = new Secret("my-secret", SecretArgs.builder()
                .setMetadata(
                        ObjectMetaArgs.builder()
                                .setNamespace(
                                        ns.getMetadata()
                                                .applyOptional(ObjectMeta::getName)
                                                .toInput())
                                .build())
                .setData(Map.of("key", Base64.getEncoder().encodeToString("value".getBytes(StandardCharsets.UTF_8))))
                .build(),
                CustomResourceOptions.builder()
                        .setProvider(k8sProvider)
                        .build()
        );

        this.theSecret = secret.getMetadata().applyOptional(ObjectMeta::getName);
        this.theSecretData = secret.getData().applyValue(
                d -> d.entrySet().stream().peek(System.out::println).collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                )));
    }
}

package org.example;

import io.pulumi.Config;
import io.pulumi.Stack;
import io.pulumi.core.Output;
import io.pulumi.core.internal.annotations.OutputExport;
import io.pulumi.kubernetes.core.v1.Namespace;
import io.pulumi.kubernetes.core.v1.Secret;
import io.pulumi.kubernetes.core.v1.inputs.NamespaceArgs;
import io.pulumi.kubernetes.core.v1.inputs.SecretArgs;
import io.pulumi.kubernetes.meta.v1.inputs.ObjectMetaArgs;
import io.pulumi.kubernetes.meta.v1.outputs.ObjectMeta;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class MyStack extends Stack {

    @OutputExport(type = String.class)
    public final Output<String> theNamespace;

    @OutputExport(type = String.class)
    public final Output<String> theSecret;

    @OutputExport(type = Map.class, parameters = {String.class, String.class})
    public final Output<Map<String, String>> theSecretData;

    public MyStack() {
        var config = Config.of("example-jvm");
        var name = config.require("name");

        var ns = new Namespace(name, NamespaceArgs.builder()
                .setMetadata(
                        ObjectMetaArgs.builder()
                                .setName(name)
                                .setLabels(Map.of("app", name))
                                .build()
                ).build(),
                null
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
                null
        );

        this.theSecret = secret.getMetadata().applyOptional(ObjectMeta::getName);
        this.theSecretData = secret.getData().applyValue(
                d -> d.entrySet().stream().peek(System.out::println).collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                )));
    }
}

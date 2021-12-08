package org.example;

import io.pulumi.Config;
import io.pulumi.Stack;
import io.pulumi.core.Output;
import io.pulumi.core.internal.annotations.OutputExport;
import org.example.k8s.core.v1.Namespace;
import org.example.k8s.core.v1.Secret;
import org.example.k8s.meta.v1.ObjectMeta;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class MyStackCustom extends Stack {

    @OutputExport(type = String.class)
    public final Output<String> theNamespace;

    @OutputExport(type = String.class)
    public final Output<String> theSecret;

    @OutputExport(type = Map.class, parameters = {String.class, String.class})
    public final Output<Map<String, String>> theSecretData;

    public MyStackCustom() {
        var config = Config.of("example-jvm");
        var name = config.require("name");

        var ns = new Namespace(name, Namespace.args()
                .setMetadata(
                        ObjectMeta.args()
                                .setName(name)
                                .setLabels(Map.of("app", name))
                                .build()
                ).build()
        );

        this.theNamespace = Output.allOutputs(
                ns.getApiVersion(),
                ns.getKind(),
                ns.getMetadata().applyOptional(ObjectMeta.Result::getName)
        ).applyValue(ss -> String.join("/", ss));

        var secret = new Secret("my-secret", Secret.args()
                .setMetadata(
                        ObjectMeta.args()
                                .setNamespace(
                                        ns.getMetadata()
                                                .applyOptional(ObjectMeta.Result::getName)
                                                .toInput())
                                .build())
                .setData(Map.of("key", Base64.getEncoder().encodeToString("value".getBytes(StandardCharsets.UTF_8))))
                .build()
        );

        this.theSecret = secret.getMetadata().applyOptional(ObjectMeta.Result::getName);
        this.theSecretData = secret.getData().applyValue(
                d -> d.entrySet().stream().peek(System.out::println).collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                )));
    }
}

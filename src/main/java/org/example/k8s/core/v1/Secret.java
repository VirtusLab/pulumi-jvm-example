package org.example.k8s.core.v1;

import io.pulumi.core.Input;
import io.pulumi.core.Output;
import io.pulumi.core.internal.annotations.InputImport;
import io.pulumi.core.internal.annotations.OutputExport;
import io.pulumi.core.internal.annotations.ResourceType;
import io.pulumi.resources.CustomResourceOptions;
import org.example.k8s.KubernetesResource;
import org.example.k8s.Utilities;
import org.example.k8s.meta.v1.ObjectMeta;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@ResourceType(type = "kubernetes:core/v1:Secret")
public class Secret extends KubernetesResource {
    @OutputExport(name = "apiVersion", type = String.class)
    @Nullable
    private Output<String> apiVersion;

    @OutputExport(name = "kind", type = String.class)
    @Nullable
    private Output<String> kind;

    @OutputExport(name = "metadata", type = ObjectMeta.Result.class)
    @Nullable
    private Output<ObjectMeta.Result> metadata;

    @OutputExport(name = "data", type = Map.class, parameters = {String.class, String.class})
    @Nullable
    private Output<Map<String, String>> data;

    public Secret(String name, @Nullable Secret.Args args) {
        this(name, args, null);
    }

    public Secret(String name, @Nullable Secret.Args args, @Nullable CustomResourceOptions options) {
        super("kubernetes:core/v1:Secret", name, makeArgs(args), makeResourceOptions(options, null));
    }

    private Secret(String name, Input<String> id, @Nullable CustomResourceOptions options) {
        super("kubernetes:core/v1:Secret", name, null, makeResourceOptions(options, id));
    }

    public Output<String> getApiVersion() {
        return Output.ofNullable(this.apiVersion);
    }

    public Output<String> getKind() {
        return Output.ofNullable(this.kind);
    }

    public Output<ObjectMeta.Result> getMetadata() {
        return Output.ofNullable(this.metadata);
    }

    public Output<Map<String, String>> getData() {
        return Output.ofNullable(this.data);
    }

    private static Secret.Args makeArgs(@Nullable Secret.Args args) {
        var builder = args == null ? Secret.Args.builder() : Secret.Args.builder(args);
        return builder
                .setApiVersion("v1")
                .setKind("Secret")
                .build();
    }

    private static CustomResourceOptions makeResourceOptions(@Nullable CustomResourceOptions options, @Nullable Input<String> id) {
        var defaultOptions = CustomResourceOptions.builder()
                .setVersion(Utilities.getVersion())
                .setAdditionalSecretOutputs(
                        List.of("data", "stringData")
                )
                .build();
        // Override the ID if one was specified for consistency with other language SDKs.
        return CustomResourceOptions.merge(defaultOptions, options, id);
    }

    public static Secret get(String name, Input<String> id, @Nullable CustomResourceOptions options) {
        return new Secret(name, id, options);
    }

    public static Secret.Args.Builder args() {
        return Secret.Args.builder();
    }

    public static final class Args extends io.pulumi.resources.ResourceArgs {
        public static final Args Empty = Args.builder().build();

        @InputImport(name = "apiVersion")
        private final Input<String> apiVersion;

        @InputImport(name = "kind")
        private final Input<String> kind;

        @InputImport(name = "metadata")
        private final Input<ObjectMeta.Args> metadata;

        @InputImport(name = "data")
        private final Input<Map<String, String>> data;

        public Args(
                Input<String> apiVersion,
                Input<String> kind,
                Input<ObjectMeta.Args> metadata,
                Input<Map<String, String>> data) {
            this.apiVersion = Objects.requireNonNull(apiVersion);
            this.kind = Objects.requireNonNull(kind);
            this.metadata = Objects.requireNonNull(metadata);
            this.data = Objects.requireNonNull(data);
        }

        public Input<String> getApiVersion() {
            return this.apiVersion;
        }

        public Input<String> getKind() {
            return this.kind;
        }

        public Input<ObjectMeta.Args> getMetadata() {
            return this.metadata;
        }

        public static Args.Builder builder() {
            return new Args.Builder();
        }

        private static Args.Builder builder(Args args) {
            return new Args.Builder(args);
        }

        public static final class Builder {
            private Input<String> apiVersion = Input.empty();
            private Input<String> kind = Input.empty();
            private Input<ObjectMeta.Args> metadata = Input.empty();
            private Input<Map<String, String>> data = Input.<Map<String, String>>empty().asSecret();

            private Builder(Args args) {
                Objects.requireNonNull(args);
                this.apiVersion = Objects.requireNonNull(args.kind);
                this.kind = Objects.requireNonNull(args.kind);
                this.metadata = Objects.requireNonNull(args.metadata);
                this.data = Objects.requireNonNull(args.data);
            }

            public Builder() {
                // Empty
            }

            public Args.Builder setApiVersion(@Nullable Input<String> apiVersion) {
                this.apiVersion = Input.ofNullable(apiVersion);
                return this;
            }

            public Args.Builder setApiVersion(@Nullable String apiVersion) {
                this.apiVersion = Input.ofNullable(apiVersion);
                return this;
            }

            public Args.Builder setKind(@Nullable Input<String> kind) {
                this.kind = Input.ofNullable(kind);
                return this;
            }

            public Args.Builder setKind(@Nullable String kind) {
                this.kind = Input.ofNullable(kind);
                return this;
            }

            public Args.Builder setMetadata(@Nullable Input<ObjectMeta.Args> metadata) {
                this.metadata = Input.ofNullable(metadata);
                return this;
            }

            public Args.Builder setMetadata(@Nullable ObjectMeta.Args metadata) {
                this.metadata = Input.ofNullable(metadata);
                return this;
            }

            public Args.Builder setData(@Nullable Input<Map<String, String>> data) {
                this.data = Input.ofNullable(data).asSecret();
                return this;
            }

            public Args.Builder setData(@Nullable Map<String, String> data) {
                this.data = Input.ofNullable(data).asSecret();
                return this;
            }

            public Args build() {
                return new Args(this.apiVersion, this.kind, this.metadata, this.data);
            }
        }
    }
}

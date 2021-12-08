package org.example.k8s.core.v1;

import io.pulumi.core.Input;
import io.pulumi.core.Output;
import io.pulumi.core.internal.annotations.InputImport;
import io.pulumi.core.internal.annotations.OutputCustomType;
import io.pulumi.core.internal.annotations.OutputExport;
import io.pulumi.core.internal.annotations.ResourceType;
import io.pulumi.resources.CustomResourceOptions;
import org.example.k8s.KubernetesResource;
import org.example.k8s.Utilities;
import org.example.k8s.meta.v1.ObjectMeta;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Optional;

@ResourceType(type = "kubernetes:core/v1:Namespace")
public final class Namespace extends KubernetesResource {

    @OutputExport(name = "apiVersion", type = String.class)
    @Nullable
    private Output<String> apiVersion;

    @OutputExport(name = "kind", type = String.class)
    @Nullable
    private Output<String> kind;

    @OutputExport(name = "metadata", type = ObjectMeta.Result.class)
    @Nullable
    private Output<ObjectMeta.Result> metadata;

    public Namespace(String name) {
        this(name, null);
    }

    public Namespace(String name, @Nullable Args args) {
        this(name, args, null);
    }

    public Namespace(String name, @Nullable Args args, @Nullable CustomResourceOptions options) {
        super("kubernetes:core/v1:Namespace", name, makeArgs(args), makeResourceOptions(options, null));
    }

    private Namespace(String name, Input<String> id, @Nullable CustomResourceOptions options) {
        super("kubernetes:core/v1:Namespace", name, null, makeResourceOptions(options, id));
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

    private static Args makeArgs(@Nullable Args args) {
        var builder = args == null ? Args.builder() : Args.builder(args);
        return builder
                .setApiVersion("v1")
                .setKind("Namespace")
                .build();
    }

    private static CustomResourceOptions makeResourceOptions(@Nullable CustomResourceOptions options, @Nullable Input<String> id) {
        var defaultOptions = CustomResourceOptions.builder()
                .setVersion(Utilities.getVersion())
                .build();
        // Override the ID if one was specified for consistency with other language SDKs.
        return CustomResourceOptions.merge(defaultOptions, options, id);
    }

    public static Namespace get(String name, Input<String> id, @Nullable CustomResourceOptions options) {
        return new Namespace(name, id, options);
    }

    public static Namespace.Args.Builder args() {
        return Namespace.Args.builder();
    }

    public static final class Args extends io.pulumi.resources.ResourceArgs {
        public static final Args Empty = Args.builder().build();

        @InputImport(name = "apiVersion")
        private final Input<String> apiVersion;

        @InputImport(name = "kind")
        private final Input<String> kind;

        @InputImport(name = "metadata")
        private final Input<ObjectMeta.Args> metadata;

        public Args(
                Input<String> apiVersion,
                Input<String> kind,
                Input<ObjectMeta.Args> metadata
        ) {
            this.apiVersion = Objects.requireNonNull(apiVersion);
            this.kind = Objects.requireNonNull(kind);
            this.metadata = Objects.requireNonNull(metadata);
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

        public static Builder builder() {
            return new Builder();
        }

        private static Builder builder(Args args) {
            return new Builder(args);
        }

        public static final class Builder {
            private Input<String> apiVersion = Input.empty();
            private Input<String> kind = Input.empty();
            private Input<ObjectMeta.Args> metadata = Input.empty();

            private Builder(Args args) {
                Objects.requireNonNull(args);
                this.apiVersion = Objects.requireNonNull(args.kind);
                this.kind = Objects.requireNonNull(args.kind);
                this.metadata = Objects.requireNonNull(args.metadata);
            }

            public Builder() {
                // Empty
            }

            public Builder setApiVersion(@Nullable Input<String> apiVersion) {
                this.apiVersion = Input.ofNullable(apiVersion);
                return this;
            }

            public Builder setApiVersion(@Nullable String apiVersion) {
                this.apiVersion = Input.ofNullable(apiVersion);
                return this;
            }

            public Builder setKind(@Nullable Input<String> kind) {
                this.kind = Input.ofNullable(kind);
                return this;
            }

            public Builder setKind(@Nullable String kind) {
                this.kind = Input.ofNullable(kind);
                return this;
            }

            public Builder setMetadata(@Nullable Input<ObjectMeta.Args> metadata) {
                this.metadata = Input.ofNullable(metadata);
                return this;
            }

            public Builder setMetadata(@Nullable ObjectMeta.Args metadata) {
                this.metadata = Input.ofNullable(metadata);
                return this;
            }

            public Args build() {
                return new Args(this.apiVersion, this.kind, this.metadata);
            }
        }
    }

    @OutputCustomType
    public static final class Result {
        @Nullable
        private final String apiVersion;
        @Nullable
        private final String kind;
        @Nullable
        private final ObjectMeta.Result metadata;

        @OutputCustomType.Constructor({"name", "labels", "annotations"})
        public Result(
                @Nullable String apiVersion,
                @Nullable String kind,
                @Nullable ObjectMeta.Result metadata
        ) {
            this.apiVersion = apiVersion;
            this.kind = kind;
            this.metadata = metadata;
        }

        public Optional<String> getApiVersion() {
            return Optional.ofNullable(apiVersion);
        }

        public Optional<String> getKind() {
            return Optional.ofNullable(kind);
        }

        public Optional<ObjectMeta.Result> getMetadata() {
            return Optional.ofNullable(metadata);
        }
    }
}

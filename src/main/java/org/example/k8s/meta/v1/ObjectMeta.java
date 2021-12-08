package org.example.k8s.meta.v1;

import io.pulumi.core.Input;
import io.pulumi.core.internal.annotations.InputImport;
import io.pulumi.core.internal.annotations.OutputCustomType;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public final class ObjectMeta {

    private ObjectMeta() {
        throw new UnsupportedOperationException("static class");
    }

    public static ObjectMeta.Args.Builder args() {
        return new Args.Builder();
    }

    public static final class Args extends io.pulumi.resources.ResourceArgs {
        public static final Args Empty = Args.builder().build();

        @InputImport(name = "name")
        private final Input<String> name;
        @InputImport(name = "namespace")
        private final Input<String> namespace;
        @InputImport(name = "labels")
        private final Input<Map<String, String>> labels;
        @InputImport(name = "annotations")
        private final Input<Map<String, String>> annotations;

        public Args(
                Input<String> name,
                Input<String> namespace,
                Input<Map<String, String>> labels,
                Input<Map<String, String>> annotations
        ) {
            this.name = Objects.requireNonNull(name);
            this.namespace = Objects.requireNonNull(namespace);
            this.labels = Objects.requireNonNull(labels);
            this.annotations = Objects.requireNonNull(annotations);
        }

        public Input<String> getName() {
            return name;
        }

        public Input<String> getNamespace() {
            return namespace;
        }

        public Input<Map<String, String>> getLabels() {
            return labels;
        }

        public Input<Map<String, String>> getAnnotations() {
            return annotations;
        }

        public static Builder builder() {
            return new Builder();
        }

        public static final class Builder {
            private Input<String> name = Input.empty();
            private Input<String> namespace = Input.empty();
            private Input<Map<String, String>> labels = Input.empty();
            private Input<Map<String, String>> annotations = Input.empty();

            public Builder(Args args) {
                Objects.requireNonNull(args);
                this.name = Objects.requireNonNull(args.name);
                this.namespace = Objects.requireNonNull(args.namespace);
                this.labels = Objects.requireNonNull(args.labels);
                this.annotations = Objects.requireNonNull(args.annotations);
            }

            public Builder() {
                // Empty
            }

            public Builder setName(@Nullable Input<String> name) {
                this.name = Input.ofNullable(name);
                return this;
            }

            public Builder setName(@Nullable String name) {
                this.name = Input.ofNullable(name);
                return this;
            }

            public Builder setNamespace(@Nullable Input<String> namespace) {
                this.namespace = Input.ofNullable(namespace);
                return this;
            }

            public Builder setNamespace(@Nullable String namespace) {
                this.namespace = Input.ofNullable(namespace);
                return this;
            }

            public Builder setLabels(Input<Map<String, String>> labels) {
                this.labels = Input.ofNullable(labels);
                return this;
            }

            public Builder setLabels(Map<String, String> labels) {
                this.labels = Input.ofNullable(labels);
                return this;
            }

            public Builder setAnnotations(Input<Map<String, String>> annotations) {
                this.annotations = Input.ofNullable(annotations);
                return this;
            }

            public Builder setAnnotations(Map<String, String> annotations) {
                this.annotations = Input.ofNullable(annotations);
                return this;
            }

            public Args build() {
                return new Args(this.name, this.namespace, this.labels, annotations);
            }
        }
    }

    @OutputCustomType
    public static final class Result {
        @Nullable
        private final String name;
        @Nullable
        private final String namespace;
        @Nullable
        private final Map<String, String> labels;
        @Nullable
        private final Map<String, String> annotations;

        @OutputCustomType.Constructor({"name", "namespace", "labels", "annotations"})
        public Result(
                @Nullable String name,
                @Nullable String namespace,
                @Nullable Map<String, String> labels,
                @Nullable Map<String, String> annotations
        ) {
            this.name = name;
            this.namespace = namespace;
            this.labels = labels;
            this.annotations = annotations;
        }

        public Optional<String> getName() {
            return Optional.ofNullable(this.name);
        }

        public Optional<String> getNamespace() {
            return Optional.ofNullable(namespace);
        }

        public Map<String, String> getLabels() {
            return this.labels == null ? Map.of() : this.labels;
        }

        public Map<String, String> getAnnotations() {
            return this.annotations == null ? Map.of() : this.annotations;
        }

        public static Builder builder() {
            return new Builder();
        }

        public static class Builder {
            @Nullable
            private String name;
            @Nullable
            private String namespace;
            @Nullable
            private Map<String, String> labels;
            @Nullable
            private Map<String, String> annotations;

            public Builder setName(@Nullable String name) {
                this.name = name;
                return this;
            }

            public Builder setNamespace(@Nullable String namespace) {
                this.namespace = namespace;
                return this;
            }

            public Builder setLabels(@Nullable Map<String, String> labels) {
                this.labels = labels;
                return this;
            }

            public Builder setAnnotations(@Nullable Map<String, String> annotations) {
                this.annotations = annotations;
                return this;
            }

            public ObjectMeta.Result build() {
                return new ObjectMeta.Result(this.name, this.namespace, this.labels, this.annotations);
            }
        }
    }
}
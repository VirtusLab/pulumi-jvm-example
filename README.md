# Pulumi Java SDK and Provider Usage Example

This example requires [experimental java sdk](https://github.com/VirtusLab/pulumi/tree/jvm-lang-codegen-rewrite) and [kubernetes provider package](https://github.com/VirtusLab/pulumi-kubernetes/tree/jvm-v3.5.1).

To start `kind` cluster:
```bash
kind create cluster --config kind.config.yaml
```

To initialize Pulumi to store its state locally run:
```bash
pulumi login --local
```

To start `pulumi` (in `debug` mode):
```bash
export PULUMI_CONFIG_PASSPHRASE="example-jvm"; pulumi up -d
```

Cleanup:
```bash
export PULUMI_CONFIG_PASSPHRASE="example-jvm"; pulumi destroy
kind delete cluster
```
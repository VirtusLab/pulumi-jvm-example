# Pulumi Java SDK and Provider Usage Example

This example requires experimental java sdk and kubernetes provider package.

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
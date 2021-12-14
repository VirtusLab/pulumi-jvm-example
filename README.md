# Pulumi Java SDK and Provider Usage Example

This example requires [experimental java sdk](https://github.com/VirtusLab/pulumi/tree/jvm-lang-codegen-rewrite) 
and [kubernetes provider package](https://github.com/VirtusLab/pulumi-kubernetes/tree/jvm-v3.5.1)
and [kind provider](https://github.com/pawelprazak/pulumi-kind) compiled and installed locally.

TL;DR: this will probably not work for you yet, but stay tuned.

To initialize Pulumi to store its state locally run:
```bash
pulumi login --local
```

To start `pulumi` (in `debug` mode):
```bash
export PULUMI_CONFIG_PASSPHRASE="example-jvm"; pulumi up -d
```

Cleanup `pulumi` (in `debug` mode):
```bash
export PULUMI_CONFIG_PASSPHRASE="example-jvm"; pulumi destroy -d
```
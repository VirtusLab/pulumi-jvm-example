package org.example.k8s;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class Utilities {

    private static final String version;
    public static String getVersion() {
        return version;
    }

    static {
        var resourceName = "org/example/k8s/version.txt";
        var versionFile = Utilities.class.getClassLoader().getResourceAsStream(resourceName);
        if (versionFile == null) {
            throw new IllegalStateException(
                    String.format("expected resource '%s' on Classpath, not found", resourceName)
            );
        }
        version = new BufferedReader(new InputStreamReader(versionFile))
                .lines()
                .collect(Collectors.joining("\n"))
                .trim();
    }
}

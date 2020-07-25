package org.xcoderelease;
public class compareXcodeVersion {


    public static class VersionStr implements Comparable<VersionStr> {
        private final String version;

        public VersionStr(String version) {
            this.version = version;
        }

        public String getVersion() {
            return this.version;
        }

        @Override
        public int compareTo(VersionStr o) {
            if (o == null) {
                return 1;
            }
            String[] thisVersions = this.version.split("\\.");
            String[] otherVersions = o.getVersion().split("\\.");
            for (int i = 0 ; i < thisVersions.length ; i++) {
                if (otherVersions.length - 1 < i) {
                    //case of 2.3.1 2.3
                    return 1;
                }
                Integer version = Integer.parseInt(thisVersions[i]);
                Integer otherVersion = Integer.parseInt(otherVersions[i]);
                if (version.compareTo(otherVersion) == 0) {
                    //If same continue next
                    continue;
                }
                return version.compareTo(otherVersion);
            }
            if (thisVersions.length == otherVersions.length) {
                return 0;
            }
            return -1;
        }
    }
}
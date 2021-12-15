package com.techelevator.model;

import java.util.Objects;

public class Execution {
    private String clientId;
    private String clientSecret;
    private String script;
    private String language;
    private String versionIndex;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getVersionIndex() {
        return versionIndex;
    }

    public void setVersionIndex(String versionIndex) {
        this.versionIndex = versionIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Execution execution = (Execution) o;
        return Objects.equals(clientId, execution.clientId) && Objects.equals(clientSecret, execution.clientSecret) && Objects.equals(script, execution.script) && Objects.equals(language, execution.language) && Objects.equals(versionIndex, execution.versionIndex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, clientSecret, script, language, versionIndex);
    }
}

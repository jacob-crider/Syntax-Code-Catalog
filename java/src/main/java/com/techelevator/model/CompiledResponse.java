package com.techelevator.model;

import java.util.Objects;

public class CompiledResponse {
    private String output;
    private String statusCode;
    private String memory;
    private String cpuTime;

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getCpuTime() {
        return cpuTime;
    }

    public void setCpuTime(String cpuTime) {
        this.cpuTime = cpuTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompiledResponse that = (CompiledResponse) o;
        return Objects.equals(output, that.output) && Objects.equals(statusCode, that.statusCode) && Objects.equals(memory, that.memory) && Objects.equals(cpuTime, that.cpuTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(output, statusCode, memory, cpuTime);
    }
}

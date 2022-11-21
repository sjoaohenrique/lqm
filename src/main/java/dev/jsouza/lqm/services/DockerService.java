package dev.jsouza.lqm.services;

import org.springframework.stereotype.Service;

import com.github.dockerjava.api.DockerClient;

@Service
public class DockerService {

    DockerClient dockerClient;

    public DockerService(DockerClient dockerClient) {
        this.dockerClient = dockerClient;
    }

    public boolean isContainerRunning(String image) {
        return dockerClient.listContainersCmd().exec().stream()
                .anyMatch(container -> container.getImage().equalsIgnoreCase(image));
    }

}

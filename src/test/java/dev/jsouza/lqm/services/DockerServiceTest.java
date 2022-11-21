package dev.jsouza.lqm.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.core.command.ListContainersCmdImpl;

@ExtendWith(MockitoExtension.class)
public class DockerServiceTest {

    private final String VALID_IMAGE = "localstack/localstack";

    @Mock
    DockerClient dockerClient;

    @InjectMocks
    DockerService service;

    @BeforeEach
    void init() {
        var containerMock = mock(Container.class);
        when(containerMock.getImage()).thenReturn(VALID_IMAGE);
        
        when(dockerClient.listContainersCmd()).thenReturn(mock(ListContainersCmdImpl.class));
        when(dockerClient.listContainersCmd().exec()).thenReturn(List.of(containerMock));
    }

    @Test
    public void whenIsContainerRunningCalledWithAValidRunningImageItShouldReturnTrue() {
        assertTrue(service.isContainerRunning(VALID_IMAGE));
    }
    
    @Test
    public void whenIsContainerRunningCalledWithAnInvalidRunningImageItShouldReturnFalse() {
        assertFalse(service.isContainerRunning("postgres-13"));
    }

    @Test
    public void whenIsContainerRunningCalledWithNullValueItShouldReturnFalse() {
        assertFalse(service.isContainerRunning(null));
    }
}

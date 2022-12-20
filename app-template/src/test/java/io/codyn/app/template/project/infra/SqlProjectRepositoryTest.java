package io.codyn.app.template.project.infra;

import io.codyn.app.template.SpringIntegrationTest;
import io.codyn.app.template.project.domain.model.Project;
import io.codyn.app.template.test.Tests;
import io.codyn.app.template.user.TestUserClient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class SqlProjectRepositoryTest extends SpringIntegrationTest {

    @Autowired
    private SqlProjectRepository projectRepository;
    @Autowired
    private TestUserClient userClient;

    @Test
    void shouldFindOwnerById() {
        var firstProject = prepareNewProject();
        var secondProject = prepareNewProject();

        projectRepository.save(firstProject);
        projectRepository.save(secondProject);

        Assertions.assertThat(projectRepository.findOwnerById(firstProject.id()).orElseThrow())
                .isEqualTo(firstProject.ownerId());

        Assertions.assertThat(projectRepository.findOwnerById(secondProject.id()).orElseThrow())
                .isEqualTo(secondProject.ownerId());
    }

    @Test
    void shouldDeleteProjectById() {
        var firstProject = prepareNewProject();
        var secondProject = prepareNewProject();

        projectRepository.save(firstProject);
        projectRepository.save(secondProject);

        projectRepository.delete(firstProject.id());

        Assertions.assertThat(projectRepository.findById(firstProject.id())).isEmpty();

        Assertions.assertThat(projectRepository.findById(secondProject.id())).isNotEmpty();
    }

    private Project prepareNewProject() {
        var ownerId = UUID.randomUUID();

        userClient.createRandomUser(ownerId);

        return new Project(UUID.randomUUID(), ownerId, Tests.randomString(), 0L);
    }

}

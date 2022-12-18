package io.codyn.app.template.project.app;

import io.codyn.app.template.project.app.model.ApiNewProject;
import io.codyn.app.template.project.app.model.ApiUpdateProject;
import io.codyn.app.template.project.domain.model.AddUsersToProjectCommand;
import io.codyn.app.template.project.domain.ProjectService;
import io.codyn.app.template.project.domain.model.RemoveUsersFromProjectCommand;
import io.codyn.app.template.user.api.UserClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final UserClient userClient;

    public ProjectController(ProjectService projectService,
                             UserClient userClient) {
        this.projectService = projectService;
        this.userClient = userClient;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody ApiNewProject request) {
        var userId = userClient.currentUserId();
        projectService.save(request.toProject(userId), userId);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody ApiUpdateProject request,
                       @PathVariable UUID id) {
        var userId = userClient.currentUserId();
        projectService.save(request.toProject(id, userId), userId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        var userId = userClient.currentUserId();
        projectService.delete(id, userId);
    }

    @PostMapping("/{id}/users")
    public void addUsers(@PathVariable UUID id,
                         @RequestBody List<UUID> toAddUserIds) {
        var userId = userClient.currentUserId();
        var command = new AddUsersToProjectCommand(id, userId, toAddUserIds);
        projectService.addUsers(command);
    }

    @DeleteMapping("/{id}/users")
    public void removeUsers(@PathVariable UUID id,
                            @RequestBody List<UUID> toAddUserIds) {
        var userId = userClient.currentUserId();
        var command = new RemoveUsersFromProjectCommand(id, userId, toAddUserIds);
        projectService.removeUsers(command);
    }
}
package com.example.toshiten.controller.system;

import com.example.toshiten.service.accesscontrol.AccessControlService;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sys")
@RequiredArgsConstructor
public class SystemController {

    private final AccessControlService accessControlService;

    @GetMapping("/roles")
    public String listRole(Model model) {
        var roleList = accessControlService.findAllRoles()
                .stream()
                .map(RoleListElementDTO::from)
                .toList();
        model.addAttribute("roleList", roleList);
        return "sys/roles/list";
    }

    @GetMapping("/roles/{id}")
    public String showRole(
            @PathVariable long id,
            @RequestHeader(name = "hx-request", required = false, defaultValue = "false") boolean isHxRequest,
            Model model
    ) {
        accessControlService.findRoleById(id)
                .map(roleEntity -> RoleDTO.from(roleEntity, accessControlService.findAllPermissions()))
                .ifPresentOrElse(
                        roleDTO -> {
                            model.addAttribute("role", roleDTO);
                            model.addAttribute("isEditMode", false);
                        },
                        () -> {
                            throw new IllegalArgumentException("Role not found"); // TODO custom exception and error page
                        }
                );

        return isHxRequest ? "sys/roles/detail :: permission-view" : "sys/roles/detail";
    }

    @GetMapping("/roles/{id}/edit")
    public String showEditForm(
            @PathVariable long id,
            @RequestHeader(name = "hx-request", required = false, defaultValue = "false") boolean isHxRequest,
            Model model
    ) {
        accessControlService.findRoleById(id)
                .map(roleEntity -> RoleDTO.from(roleEntity, accessControlService.findAllPermissions()))
                .ifPresentOrElse(
                        roleDTO -> {
                            model.addAttribute("role", roleDTO);
                            model.addAttribute("isEditMode", true);
                        },
                        () -> {
                            throw new IllegalArgumentException("Role not found"); // TODO custom exception and error page
                        }
                );

        return isHxRequest ? "sys/roles/detail :: permission-view" : "sys/roles/detail";
    }

    @PutMapping("/roles/{id}")
    @HxRequest
    public String updateForHtmx(@PathVariable long id, PermissionsForm form, Model model) {
        accessControlService.updateRolePermissions(id, form.permissions());
        accessControlService.findRoleById(id)
                .map(roleEntity -> RoleDTO.from(roleEntity, accessControlService.findAllPermissions()))
                .ifPresentOrElse(
                        roleDTO -> {
                            model.addAttribute("role", roleDTO);
                            model.addAttribute("isEditMode", false);
                            model.addAttribute("isUpdateSuccess", true);
                        },
                        () -> {
                            throw new IllegalArgumentException("Role not found"); // TODO custom exception and error page
                        }
                );

        return "sys/roles/detail :: permission-view";
    }
}

package com.example.toshiten.controller.system;

import com.example.toshiten.service.accesscontrol.AccessControlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys")
@RequiredArgsConstructor
public class SystemController {

    private final AccessControlService accessControlService;

    @GetMapping("/roles")
    public String listRole(Model model) {
        model.addAttribute("roleList", accessControlService.findAllRoles());
        return "sys/roles/list";
    }

    @GetMapping("/roles/{id}")
    public String showRole(@PathVariable long id, Model model) {
        accessControlService.findRoleById(id)
                .map(roleEntity -> RoleDTO.from(roleEntity, accessControlService.findAllPermissions()))
                .ifPresentOrElse(
                        roleDTO -> model.addAttribute("role", roleDTO),
                        () -> {
                            throw new IllegalArgumentException("Role not found"); // TODO custom exception and error page
                        }
                );

        return "sys/roles/detail";
    }
}

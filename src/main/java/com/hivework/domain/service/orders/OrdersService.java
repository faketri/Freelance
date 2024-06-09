package com.hivework.domain.service.orders;

import com.hivework.domain.entity.orders.Orders;
import com.hivework.domain.entity.projects.Projects;
import com.hivework.domain.entity.user.Users;
import com.hivework.domain.repository.OrdersRepository;
import com.hivework.domain.service.project.ProjectsService;
import com.hivework.domain.service.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final ProjectsService projectsService;
    private final UserService userService;

    public OrdersService(OrdersRepository ordersRepository, ProjectsService projectsService, UserService userService) {
        this.ordersRepository = ordersRepository;
        this.projectsService = projectsService;
        this.userService = userService;
    }

    public Orders findById(Long id) {
        return ordersRepository.findById(id).orElse(null);
    }

    public Page<Orders> findAll(Pageable pageable) {
        return ordersRepository.findAll(pageable);
    }

    public Orders create(Long id, final Long userId) {
        final Projects project = projectsService.findById(id);
        final Orders orders = new Orders();
        final Users userDev = userService.findById(userId);

        orders.setProjects(project);
        orders.setDeveloper(userDev);
        orders.setActive(true);

        project.setActive(false);
        projectsService.save(project);
        return save(orders);
    }

    public Orders save(Orders entity) {
        return ordersRepository.save(entity);
    }
}

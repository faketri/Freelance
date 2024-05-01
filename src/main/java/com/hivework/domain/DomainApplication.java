package com.hivework.domain;

import com.github.javafaker.Faker;
import com.hivework.domain.dto.request.SingUpRequest;
import com.hivework.domain.entity.categories.Categories;
import com.hivework.domain.entity.categories.SubCategories;
import com.hivework.domain.entity.projects.DeveloperResponseProjects;
import com.hivework.domain.entity.projects.Projects;
import com.hivework.domain.entity.services.Services;
import com.hivework.domain.entity.skills.Skills;
import com.hivework.domain.entity.user.ERole;
import com.hivework.domain.entity.user.Users;
import com.hivework.domain.repository.SkillsRepository;
import com.hivework.domain.service.auth.AuthService;
import com.hivework.domain.service.categories.CategoriesService;
import com.hivework.domain.service.orders.OrdersService;
import com.hivework.domain.service.project.ProjectsService;
import com.hivework.domain.service.project.UserResponseService;
import com.hivework.domain.service.services.ServicesService;
import com.hivework.domain.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import org.apache.tomcat.Jar;
import org.hibernate.dialect.function.array.HSQLArraySetFunction;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@SpringBootApplication
public class DomainApplication {

	public static void main(String[] args) {
		SpringApplication.run(DomainApplication.class, args);
	}

	//@Bean
	public CommandLineRunner cmd(AuthService authService,
								 ProjectsService projectsService,
								 ServicesService servicesService,
								 UserService userService,
								 PasswordEncoder passwordEncoder,
								 CategoriesService categoriesService,
								 SkillsRepository skillsRepository,
								 UserResponseService userResponseService,
								 HttpSession session){
		return args -> {
			Faker faker = new Faker(new Locale("ru"));

			final Set<SubCategories> subCategoriesForProgramming =
					Set.of(new SubCategories(null, "Бэкенд"),
							new SubCategories(null, "Фронтенд"),
							new SubCategories(null, "Мобильное приложение"),
							new SubCategories(null, "Разработка игр"),
							new SubCategories(null, "Десктопное ПО"),
							new SubCategories(null, "Скрипты и плагины"));

			final Set<SubCategories> subCategoriesForTesting =
					Set.of(new SubCategories(null, "Сайты"),
							new SubCategories(null, "Мобильные приложение"),
							new SubCategories(null, "Десктопное приложение"),
							new SubCategories(null, "Сервера"));

			final Set<SubCategories> subCategoriesForDesigner =
					Set.of(new SubCategories(null, "Сайты"),
							new SubCategories(null, "Лендинг"),
							new SubCategories(null, "Логотип"),
							new SubCategories(null, "Иконки"),
							new SubCategories(null, "Мобильное приложение"),
							new SubCategories(null, "Полиграфия"),
							new SubCategories(null, "Векторная графика"),
							new SubCategories(null, "Анимация"));

			final Set<SubCategories> subCategoriesForMarketing =
					Set.of(new SubCategories(null, "Сайты"),
							new SubCategories(null, "Мобильные приложение"),
							new SubCategories(null, "Десктопное приложение"),
							new SubCategories(null, "Сервера"));

			final List<Categories> categories = categoriesService.save(List.of(
					new Categories(null, "Разработка", subCategoriesForProgramming),
					new Categories(null, "Тестирование", subCategoriesForTesting),
					new Categories(null, "Маркетинг", subCategoriesForMarketing),
					new Categories(null, "Дизайн", subCategoriesForDesigner)
			));

			final List<Skills> skills = skillsRepository.saveAll(List.of(
					new Skills(null, "Java"),
					new Skills(null, "Android"),
					new Skills(null, "Web"),
					new Skills(null, "Python"),
					new Skills(null, "Computer Science"),
					new Skills(null, "3D Designer"),
					new Skills(null, "Designer"),
					new Skills(null, "Animation"),
					new Skills(null, "Outsider")));


			final Users valera = new Users();
			valera.setLogin("VALERa");
			valera.setEmail("valeraZvonok@mail.ru");
			valera.getRoles().add(ERole.CUSTOMER);
			valera.setPassword(passwordEncoder.encode("123123123"));

			final Users kris = new Users();
			kris.setLogin("kris");
			kris.setEmail("krisOtmoroz@mail.ru");
			kris.getRoles().add(ERole.CUSTOMER);
			kris.setPassword(passwordEncoder.encode("123123123"));

			final Users graf = new Users();
			graf.setLogin("graf");
			graf.setEmail("grafTaynoDernyl@mail.ru");
			graf.getRoles().add(ERole.CUSTOMER);
			graf.setPassword(passwordEncoder.encode("123123123"));

			final Users valeraUser = userService.save(valera);
			final Users krisUser = userService.save(kris);
			final Users grafUser = userService.save(graf);

			krisUser.getRoles().add(ERole.FREELANCER);

			Projects projects = new Projects();

			projects.setTitle("АНИМАЦУЯ СДЕЛАЙ ДА");
			projects.setUsersCreator(valeraUser);
			projects.getSkills().addAll(List.of(skills.get(0), skills.get(1)));
			projects.setDescription("СДЕЛАЙ ЭТУ ТЕМКУ БРАТКА, ДАМ БАБОК");
			projects.setDateOfCompletion(LocalDateTime.now().plusDays(10));

			final Set<Skills> skillsForProject = new HashSet<>();
			skillsForProject.add(skills.stream().filter(s -> s.getName().equals("3D Designer")).findFirst().get());
			skillsForProject.add(skills.stream().filter(s -> s.getName().equals("Animation")).findFirst().get());
			skillsForProject.add(skills.stream().filter(s -> s.getName().equals("Outsider")).findFirst().get());

			projects.getSkills().addAll(skillsForProject);

			projects = projectsService.save(projects);

			DeveloperResponseProjects developerResponseProjects = new DeveloperResponseProjects();

			developerResponseProjects.setUsersDeveloper(krisUser);
			developerResponseProjects.setMessage("ХОЧУ УЖАСНО ЭТО СДЕЛАТЬ");
			developerResponseProjects.setProjects(projects);

			developerResponseProjects = userResponseService.save(developerResponseProjects);

			Services services = new Services();

			services.setTitle("МОБИЛКИ БРАТ");
			services.setDescription("СДЛЕАЮ ЭТО БРАТ");
			services.setSubCategories(
					categories.stream()
							.filter(categories1 -> categories1.getName().equals("Разработка"))
							.findFirst()
							.get()
							.getSubCategories()
							.stream()
							.filter(subCat -> subCat.getName().equals("Мобильное приложение"))
							.findFirst()
							.get()
			);
			services.setDeveloper(krisUser);

			servicesService.save(services);
		};
	}
}

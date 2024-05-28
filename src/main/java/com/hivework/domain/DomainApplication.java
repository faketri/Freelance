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
import com.hivework.domain.service.skills.SkillsService;
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
import java.util.*;

@SpringBootApplication
public class DomainApplication {

	public static void main(String[] args) {
		SpringApplication.run(DomainApplication.class, args);
	}

	@Bean
	public CommandLineRunner cmd(AuthService authService,
								 ProjectsService projectsService,
								 ServicesService servicesService,
								 UserService userService,
								 PasswordEncoder passwordEncoder,
								 CategoriesService categoriesService,
								 SkillsService skillsRepository,
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
		              new SubCategories(null, "Скрипты"),
		              new SubCategories(null, "Десктопное приложение"),
		              new SubCategories(null, "Скрипты и плагины"));
		
		      final Set<SubCategories> subCategoriesForDesigner =
		          Set.of(new SubCategories(null, "Сайты"),
		              new SubCategories(null, "Лендинг"),
		              new SubCategories(null, "Логотип"),
		              new SubCategories(null, "Иконки"),
		              new SubCategories(null, "Мобильное приложение"),
		              new SubCategories(null, "Полиграфия"),
		              new SubCategories(null, "Векторная графика"),
		              new SubCategories(null, "Типографика"),
		              new SubCategories(null, "Обложки"),
		              new SubCategories(null, "Векторные изображения"),
		              new SubCategories(null, "Анимация"));
		
		      final Set<SubCategories> subCategoriesForMarketing =
		          Set.of(new SubCategories(null, "Сайты"),
		              new SubCategories(null, "Мобильные приложение"),
		              new SubCategories(null, "Десктопное приложение"),
		              new SubCategories(null, "Аналитика"),
		              new SubCategories(null, "Сервера"));
		
		      final Set<SubCategories> subCategoriesForPhotography =
		          Set.of(new SubCategories(null, "Редактирование фото"),
		              new SubCategories(null, "Исправление фото"),
		              new SubCategories(null, "Ретушь"),
		              new SubCategories(null, "Фото документы"));
		
		      final Set<SubCategories> subCategoriesForVideo =
		          Set.of(new SubCategories(null, "Монтаж"),
		              new SubCategories(null, "3D анимация"),
		              new SubCategories(null, "2D анимация"),
		              new SubCategories(null, "Режессура"),
		              new SubCategories(null, "Сценарии"));
		
		      final Set<SubCategories> subCategoriesForMusic =
		          Set.of(new SubCategories(null, "Написание бита"),
		              new SubCategories(null, "Написание трека"),
		              new SubCategories(null, "Сведение"),
		              new SubCategories(null, "Звукорежессура"));
		
		      final Set<SubCategories> subCategoriesForCopywriting =
		          Set.of(new SubCategories(null, "Перепись текста"),
		              new SubCategories(null, "Написание текста"),
		              new SubCategories(null, "Перевод текста"));
		
		      final Set<SubCategories> subCategoriesForBusiness =
		          Set.of(new SubCategories(null, "Введение отчетов"),
		              new SubCategories(null, "Бизнес логика"),
		              new SubCategories(null, "Маштабирование"));
		
		      final List<Categories> categories = categoriesService.save(List.of(
		          new Categories(null, "Программирование и технологии", subCategoriesForProgramming),
		          new Categories(null, "Маркетинг", subCategoriesForMarketing),
		          new Categories(null, "Дизайн и графика", subCategoriesForDesigner),
		          new Categories(null, "Фотография", subCategoriesForPhotography),
		          new Categories(null, "Видео и анимация", subCategoriesForVideo),
		          new Categories(null, "Музыка и звук", subCategoriesForMusic),
		          new Categories(null, "Копирайтинг и перевод", subCategoriesForCopywriting),
		          new Categories(null, "Бизнеc", subCategoriesForBusiness)
		      ));


		final List<Skills> skills = skillsRepository.save(List.of(
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
		projects.getSkills().addAll(skills.subList(0, 2));
		projects.setDescription("СДЕЛАЙ ЭТУ ТЕМКУ БРАТКА, ДАМ БАБОК");
		projects.setDateOfCompletion(LocalDateTime.now().plusDays(10));

		//projects.getSkills().addAll(skillsForProject);
		System.out.println(projects);
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

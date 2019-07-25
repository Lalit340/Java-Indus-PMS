package in.co.indusnet.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.co.indusnet.project.model.ProjectModel;

public interface ProjectRepository extends JpaRepository<ProjectModel, Integer> {

}

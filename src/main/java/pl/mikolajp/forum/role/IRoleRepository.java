package pl.mikolajp.forum.role;

import org.springframework.data.jpa.repository.JpaRepository;

interface IRoleRepository extends JpaRepository<RoleDao, Integer> {
}

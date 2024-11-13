package com.introcopilot.user.token;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends MongoRepository<Token, String> {

	@Query("""

						select t from Token t inner join User u on t.user.id = u.id where u.id = :userId and (t.expired = false or t.revoked=false)
			""")
	List<Token> findAllValidTokenByUser(@Param("userId") String userId);

	Optional<Token> findByToken(String token);
}

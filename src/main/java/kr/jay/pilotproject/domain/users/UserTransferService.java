// package kr.jay.pilotproject.domain.users;
//
// import java.util.List;
//
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Propagation;
// import org.springframework.transaction.annotation.Transactional;
//
// // import kr.jay.pilotproject.common.config.multidatasource.DataSourceContextHolder;
// // import kr.jay.pilotproject.common.config.multidatasource.EdcDataSource;
// import kr.jay.pilotproject.infrastructure.persistance.users.UserStore;
// import lombok.RequiredArgsConstructor;
//
// /**
//  * UserTransferService
//  *
//  * @author jaypark
//  * @version 1.0.0
//  * @since 11/17/23
//  */
// @Service
// @RequiredArgsConstructor
// public class UserTransferService {
//
// 	private final UserStore userStore;
//
// 	@Transactional(propagation = Propagation.REQUIRES_NEW)
// 	public void transferUserData(String target, List<User> users) {
// 		DataSourceContextHolder.setDataSource(EdcDataSource.valueOf(target));
// 		users.forEach(userStore::save);
// 	}
//
// }

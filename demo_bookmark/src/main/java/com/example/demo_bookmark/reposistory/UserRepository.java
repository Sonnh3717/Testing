package com.example.demo_bookmark.reposistory;
import com.example.demo_bookmark.model.UserEntity;
import com.example.demo_bookmark.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repository là tầng truy cập dữ liệu (DAO).
 *
 * Vì sao Repository là interface?
 * - Spring Data JPA sẽ tự "generate" class implementation runtime (proxy) dựa trên interface này.
 * - Bạn chỉ cần khai báo method theo quy ước tên (findByUsername), Spring tự tạo query.
 * - Dễ test (mock interface), dễ thay DB layer mà không đổi logic tầng trên.
 */
//JpaRepository - là interface cung cấp các câu query. - được gọi dưới dạng hàm
    // Có thể hiểu extends là bước đăng kí, Entity, DTtype của PK tương ứng vào JPA để
    //Nó biết đây là truy cập của bảng nào.
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Optional<User> nghĩa là:
     * - Có thể tìm thấy user -> Optional chứa User
     * - Không tìm thấy -> Optional rỗng (empty)
     *
     * Lý do dùng Optional:
     * - Tránh return null rồi gây NullPointerException
     * - Ép người dùng phải xử lý case "không có dữ liệu"
     *
     * findByUsername:
     * - Spring Data JPA sẽ đọc tên hàm:
     *   "findBy" + "Username" => query theo cột username
     * - Tương đương SQL (ý nghĩa):
     *   SELECT * FROM users WHERE username = ?
     */
    Optional<UserEntity> findByUsername(String username);

    //Tự sinh query: select count(u) > 0 from UserEntity u where u.username = :username
    boolean existsByUsername(String username);

}
package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Entity representing a user in the system.
 * Implements UserDetails for Spring Security integration.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message="Full Name is required!")
	private String fullName;

	@NotEmpty(message = "Email is required!")
	@Email(message = "Enter a valid email")
	private String email;

	@NotEmpty(message = "Password is required!")
	@Size(min=8, max=128, message ="Password must be between 8 and 128 characters")
	private String password;

	@Enumerated(EnumType.STRING)
	private Role role;



	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;
	@OneToMany(mappedBy = "postedBy", fetch = FetchType.LAZY)
	private List<Post> createdPosts;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "likes", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "post_id"))
	private List<Post> likedPosts;

	/**
	 * Sets the createdAt field to the current date before persisting the entity.
	 */
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	/**
	 * Sets the updatedAt field to the current date before updating the entity.
	 */
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

	/**
	 * Returns the authorities granted to the user based on their role.
	 *
	 * @return Collection of GrantedAuthority representing the user's role
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	/**
	 * Returns the username of the user, which is their email.
	 *
	 * @return the email of the user
	 */
	@Override
	public String getUsername() {
		return email;
	}

	/**
	 * Indicates whether the user's account is expired.
	 *
	 * @return true, indicating the account is not expired
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * Indicates whether the user's account is locked.
	 *
	 * @return true, indicating the account is not locked
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * Indicates whether the user's credentials are expired.
	 *
	 * @return true, indicating the credentials are not expired
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * Indicates whether the user's account is enabled.
	 *
	 * @return true, indicating the account is enabled
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}
}

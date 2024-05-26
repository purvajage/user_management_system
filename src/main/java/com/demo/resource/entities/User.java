package com.demo.resource.entities;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.websocket.OnMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@Size( min=5, max=20, message = "Please enter the name between 5-20 characters.")
	@NotBlank(message = "Name column must not be blank.")
	@NotEmpty(message = "Name column must not be empty.")
	private String fullName;
	
	@NotBlank(message = "E-mail must not be blank.")
	@NotEmpty(message = "E-mail must not be empty.")
	@Email(message = "Please enter valid email format.")
	@Column(unique = true)
	private String email;
	
	@NotBlank(message = "Contact must not be blank.")
	@NotEmpty(message = "Contact must not be empty.")
	@Pattern(regexp = "^\\d{10}$",message = "Please enter proper contact")
	@Column(unique = true)
	private String mobile;
	
	@Size( min=15, max=40, message = "Please enter the address between 15-40 characters.")
	@NotBlank(message = "Address must not be blank.")
	@NotEmpty(message = "Address must not be empty.")
	private String address;
	
	@Min(value = 100000,message = "Please enter valid pincode")
	@Max(value = 999999,message = "Please enter valid pincode")
	@Pattern(regexp = "^\\d{6}$",message = "Please enter proper pincode")
	private String pincode;
	
	@Size( min=5, max=20, message = "Please enter the city between 5-20 characters.")
	@NotBlank(message = "city column must not be blank.")
	@NotEmpty(message = "city column must not be empty.")
	private String city;
	
	@Size( min=5, max=20, message = "Please enter the state between 5-20 characters.")
	@NotBlank(message = "state column must not be blank.")
	@NotEmpty(message = "state column must not be empty.")
	private String state;
	
	@Size( min=5, max=20, message = "Please enter the country between 5-20 characters.")
	@NotBlank(message = "country column must not be blank.")
	@NotEmpty(message = "country column must not be empty.")
	private String country;
	
	@Lob
	@Column(length = 100000000)
	@JsonIgnore
	private byte[] userPic;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Roles> roles=new ArrayList<>();
	
}

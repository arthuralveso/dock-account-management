package com.pi.accountmanagement.domains;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "peoples")
public class People implements Serializable{

	 private static final long serialVersionUID = 1L;

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long peopleId;

	    @Column(nullable = false, length = 40)
	    private String name;

	    @Column(nullable = false, length = 11)
	    private String cpf;

	    @Column(nullable = false)
	    private Date birthDate;


	
}

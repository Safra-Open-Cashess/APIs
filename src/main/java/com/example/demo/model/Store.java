package com.example.demo.model;

import javax.persistence.*;

import lombok.*;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Store
{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cnpj;
	private String nomeFantasia;
	private String razaoSocial;
	private Boolean active;
	@OneToMany
	@JoinColumn(name = "storeId")
	private List<Transaction> transactions;
}
package br.com.escoladabiblia.util.impressao;

import java.io.Serializable;

public class MateriaisPostagem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String material;

	private Long totalMaterial;

	public MateriaisPostagem(String material, Long totalMaterial) {
		this.material = material;
		this.totalMaterial = totalMaterial;
	}

	public String getMaterial() {
		return material;
	}

	public Long getTotalMaterial() {
		return totalMaterial;
	}

}

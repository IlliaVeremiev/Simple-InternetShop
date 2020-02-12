package veil.internetshop.simple.data;

import lombok.Data;

import java.io.Serializable;

@Data
public class SearchData implements Serializable{

	private String minPrice;

	private String maxPrice;

	private String category;

	private String searchText;
}

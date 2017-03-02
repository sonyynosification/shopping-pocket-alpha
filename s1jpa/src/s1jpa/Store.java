package s1jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the store database table.
 * 
 */
@Entity
@NamedQuery(name="Store.findAll", query="SELECT s FROM Store s")
public class Store implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="strore_id")
	private int stroreId;

	@Column(name="strore_address")
	private String stroreAddress;

	@Column(name="strore_email")
	private String stroreEmail;

	@Column(name="strore_name")
	private String stroreName;

	@Column(name="strore_phone")
	private String strorePhone;

	//bi-directional many-to-many association to Category
	@ManyToMany
	@JoinTable(
		name="category_in_store"
		, joinColumns={
			@JoinColumn(name="strore_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="cat_id")
			}
		)
	private List<Category> categories;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="store")
	private List<Comment> comments;

	//bi-directional many-to-many association to Mall
	@ManyToMany
	@JoinTable(
		name="mall_has_store"
		, joinColumns={
			@JoinColumn(name="strore_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="mall_id")
			}
		)
	private List<Mall> malls;

	//bi-directional many-to-many association to Brand
	@ManyToMany
	@JoinTable(
		name="store_in_brand"
		, joinColumns={
			@JoinColumn(name="strore_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="brand_id")
			}
		)
	private List<Brand> brands;

	public Store() {
	}

	public int getStroreId() {
		return this.stroreId;
	}

	public void setStroreId(int stroreId) {
		this.stroreId = stroreId;
	}

	public String getStroreAddress() {
		return this.stroreAddress;
	}

	public void setStroreAddress(String stroreAddress) {
		this.stroreAddress = stroreAddress;
	}

	public String getStroreEmail() {
		return this.stroreEmail;
	}

	public void setStroreEmail(String stroreEmail) {
		this.stroreEmail = stroreEmail;
	}

	public String getStroreName() {
		return this.stroreName;
	}

	public void setStroreName(String stroreName) {
		this.stroreName = stroreName;
	}

	public String getStrorePhone() {
		return this.strorePhone;
	}

	public void setStrorePhone(String strorePhone) {
		this.strorePhone = strorePhone;
	}

	public List<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setStore(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setStore(null);

		return comment;
	}

	public List<Mall> getMalls() {
		return this.malls;
	}

	public void setMalls(List<Mall> malls) {
		this.malls = malls;
	}

	public List<Brand> getBrands() {
		return this.brands;
	}

	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}

}
package s1jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the mall database table.
 * 
 */
@Entity
@NamedQuery(name="Mall.findAll", query="SELECT m FROM Mall m")
public class Mall implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="mall_id")
	private int mallId;

	@Column(name="mall_location")
	private String mallLocation;

	@Column(name="mall_name")
	private String mallName;

	//bi-directional many-to-many association to Store
	@ManyToMany(mappedBy="malls")
	private List<Store> stores;

	public Mall() {
	}

	public int getMallId() {
		return this.mallId;
	}

	public void setMallId(int mallId) {
		this.mallId = mallId;
	}

	public String getMallLocation() {
		return this.mallLocation;
	}

	public void setMallLocation(String mallLocation) {
		this.mallLocation = mallLocation;
	}

	public String getMallName() {
		return this.mallName;
	}

	public void setMallName(String mallName) {
		this.mallName = mallName;
	}

	public List<Store> getStores() {
		return this.stores;
	}

	public void setStores(List<Store> stores) {
		this.stores = stores;
	}

}
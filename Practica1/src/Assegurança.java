import java.text.DateFormat;
import java.util.Date;

public class Assegurança {

	private Date dateEmissio; 
	private int numeroPolissa;
	private Client client;
	private float valorImport;
	private Agent corredor;
	
	public Date getDateEmissio() {
		return dateEmissio;
	}
	public void setDateEmissio(Date dateEmissio) {
		this.dateEmissio = dateEmissio;
	}


	public int getNumeroPolissa() {
		return numeroPolissa;
	}
	public void setNumeroPolissa(int numeroPolissa) {
		this.numeroPolissa = numeroPolissa;
	}


	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}


	public float getValorImport() {
		return valorImport;
	}
	public void setValorImport(float valorImport) {
		this.valorImport = valorImport;
	}


	public Agent getCorredor() {
		return corredor;
	}
	public void setCorredor(Agent corredor) {
		this.corredor = corredor;
	}

	public Assegurança(Date dateEmissio, 
	 Client client){
		this.dateEmissio = dateEmissio; 
		this.client = client;
	}
	
	public Assegurança(Date dateEmissio, 
		 Client client,
		 float valorImport,
		 Agent corredor){
		
		this.dateEmissio = dateEmissio; 
		this.client = client;
		this.valorImport = valorImport;
		this.corredor = corredor;		
	}
	
	public String toString(){
		DateFormat df = DateFormat.getDateInstance();
		return  "\n polissa: "+ this.getNumeroPolissa()
		+"\n dateEmissio: "+ df.format(this.getDateEmissio())
		+"\n import: "+ this.getValorImport()
		+"\n CLIENT: "+ this.getClient().toString()
		+"\n CORREDOR: "+ this.getCorredor().toString()
		;
	}	
	
	public boolean equals(Object o) {
		if (getClass() != o.getClass()) return false;
		return( ((Assegurança)o).getNumeroPolissa() ==  this.getNumeroPolissa());
	}
	


	
}

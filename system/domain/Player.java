package system.domain;

public class Player {
    
    private String name;
    private Boolean turn;
    private String token;
    private String denemeString;

    public Player(String name, String token) {
        this.name = name;
        this.turn = false;
        this.token = token;
    }

    public String getName(){
        return name;
    }
    
    public void setName(String name) {
		this.name = name;
	}

    public Boolean getTurn() {
		return turn;
	}

	public void setTurn(Boolean turn) {
		this.turn = turn;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Boolean isInTurn() {
        return turn;
    }

    public void changeTurn() {
        turn = !turn;
    }


    public String getToken() {
        return token;
    }
   
}

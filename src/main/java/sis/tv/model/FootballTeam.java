package sis.tv.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FootballTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private long id;
    private String city;
    private String name;
    private String competition;
    private String owner;
    private int stadiumCapacity;
    private byte numberOfPlayers;
    private Date createdDate;

    public FootballTeam(String city, String name, String competition, String owner, int stadiumCapacity, byte numberOfPlayers) {
        this.city = city;
        this.name = name;
        this.owner = owner;
        this.competition = competition;
        this.stadiumCapacity = stadiumCapacity;
        this.numberOfPlayers = numberOfPlayers;
        this.createdDate = new Date();
    }

    public FootballTeam() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCompetition() {
        return competition;
    }

    public void setCompetition(String competition) {
        this.competition = competition;
    }

    public int getStadiumCapacity() {
        return stadiumCapacity;
    }

    public void setStadiumCapacity(int stadiumCapacity) {
        this.stadiumCapacity = stadiumCapacity;
    }

    public byte getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(byte numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FootballTeam other = (FootballTeam) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FootballTeam{" + "city=" + city + ", name=" + name + ", competition=" + competition + ", owner=" + owner + ", stadiumCapacity=" + stadiumCapacity + ", numberOfPlayers=" + numberOfPlayers + ", createdDate=" + createdDate + '}';
    }

}

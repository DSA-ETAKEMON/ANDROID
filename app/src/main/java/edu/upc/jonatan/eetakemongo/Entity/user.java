package edu.upc.jonatan.eetakemongo.Entity;

import java.util.List;

/**
 * Created by Jonatan on 26/12/2016.
 */

public class user {
        private int id;
        private String nick;
        private String password;
        private String email;
        private boolean isAdmin;
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public String getTotalEtakemons() {
            return totalEtakemons;
        }

        public void setTotalEtakemons(String totalEtakemons) {
            this.totalEtakemons = totalEtakemons;
        }

        public String getPuntuacionTotal() {
            return puntuacionTotal;
        }

        public void setPuntuacionTotal(String puntuacionTotal) {
            this.puntuacionTotal = puntuacionTotal;
        }

        private String surname;
        private String totalEtakemons;
        private String puntuacionTotal;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNick() {
            return nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public boolean getIsAdmin() {
            return isAdmin;
        }

        public void setIsAdmin(boolean isAdmin) {
            this.isAdmin = isAdmin;
        }

}

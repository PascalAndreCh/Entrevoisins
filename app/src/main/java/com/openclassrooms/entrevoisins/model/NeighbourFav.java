package com.openclassrooms.entrevoisins.model;

import java.util.Objects;

/**
 * Model object representing a Neighbour
 */
public class NeighbourFav {

    /** Identifier */
    private long idFav;

    /** Full name */
    private String nameFav;

    /** Avatar */
    private String avatarUrlFav;

    /** Adress */
    private String addressFav;

    /** Phone number */
    private String phoneNumberFav;

    /** About me */
    private String aboutMeFav;

    /**
     * Constructor
     * @param idFav
     * @param nameFav
     * @param avatarUrlFav
     */
    public NeighbourFav(long idFav, String nameFav, String avatarUrlFav, String addressFav,
                        String phoneNumberFav, String aboutMeFav) {
        this.idFav = idFav;
        this.nameFav = nameFav;
        this.avatarUrlFav = avatarUrlFav;
        this.addressFav = addressFav;
        this.phoneNumberFav = phoneNumberFav;
        this.aboutMeFav = aboutMeFav;
    }

    public long getId() {
        return idFav;
    }

    public void setId(long idFav) {
        this.idFav = idFav;
    }

    public String getName() {
        return nameFav;
    }

    public void setName(String nameFav) {
        this.nameFav = nameFav;
    }

    public String getAvatarUrl() {
        return avatarUrlFav;
    }

    public void setAvatarUrl(String avatarUrlFav) {
        this.avatarUrlFav = avatarUrlFav;
    }

    public String getAddress() {
        return addressFav;
    }

    public void setAddress(String addressFav) {
        this.addressFav = addressFav;
    }

    public String getPhoneNumber() {
        return phoneNumberFav;
    }

    public void setPhoneNumber(String phoneNumberFav) {
        this.phoneNumberFav = phoneNumberFav;
    }

    public String getAboutMe() {
        return aboutMeFav;
    }

    public void setAboutMe(String aboutMeFav) {
        this.aboutMeFav = aboutMeFav;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NeighbourFav mNeighbourFav = (NeighbourFav) o;
        return Objects.equals(idFav, mNeighbourFav.idFav);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFav);
    }
}

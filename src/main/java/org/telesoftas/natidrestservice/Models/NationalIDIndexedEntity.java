package org.telesoftas.natidrestservice.Models;

import javax.persistence.Id;

public abstract class NationalIDIndexedEntity {

    public abstract long getNationalID();

    public abstract void setNationalID(long nationalID);
}

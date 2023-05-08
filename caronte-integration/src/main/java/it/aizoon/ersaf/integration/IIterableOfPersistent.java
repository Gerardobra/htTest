package it.aizoon.ersaf.integration;

public interface IIterableOfPersistent extends IPersistent {

  public Iterable<IPersistent> getIterable();
}

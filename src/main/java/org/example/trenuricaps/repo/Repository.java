package org.example.trenuricaps.repo;

import java.util.List;

public interface Repository<E> {
    List<E> getAll();
}

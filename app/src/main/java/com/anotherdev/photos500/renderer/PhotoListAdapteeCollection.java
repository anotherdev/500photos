package com.anotherdev.photos500.renderer;

import com.anotherdev.photos500.api.dto.Photo;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.pedrogomez.renderers.AdapteeCollection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

public class PhotoListAdapteeCollection implements AdapteeCollection<Photo> {

    private final LinkedHashSet<Long> keys = Sets.newLinkedHashSet();
    private final ArrayList<Photo> data = Lists.newArrayList();

    private boolean showLoadMore = true;


    public void setShowLoadMore(boolean showLoadMore) {
        this.showLoadMore = showLoadMore;
    }

    @Override
    public int size() {
        final int size = data.size();
        return showLoadMore ? size + 1 : size;
    }

    @Override
    public Photo get(int index) {
        Photo photo = null;
        if (index < data.size()) {
            photo = data.get(index);
        }
        return photo;
    }

    @Override
    public boolean add(Photo element) {
        if (element != null && keys.add(element.getKey())) {
            return data.add(element);
        }
        return false;
    }

    @Override
    public boolean remove(Object element) {
        if (element instanceof Photo) {
            final Photo photo = (Photo) element;
            final Long key = photo.getKey();
            if (keys.remove(key)) {
                return data.remove(photo);
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Photo> elements) {
        boolean added = false;
        for (Photo photo : elements) {
            added |= add(photo);
        }
        return added;
    }

    @Override
    public boolean removeAll(Collection<?> elements) {
        boolean removed = false;
        for (Object o : elements) {
            removed |= remove(o);
        }
        return removed;
    }

    @Override
    public void clear() {
        keys.clear();
        data.clear();
    }
}

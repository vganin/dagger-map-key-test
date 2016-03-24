package net.ganin.dmkt.provider.handler;

import net.ganin.dmkt.provider.DumbContentProvider;

import dagger.Component;

@Component(modules = {
        UriHandlerModule.class
})
public interface UriHandlerComponent {
    void inject(DumbContentProvider to);
}

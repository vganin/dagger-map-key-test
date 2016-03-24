package net.ganin.dmkt.provider.handler;

import net.ganin.dmkt.provider.DumbContentProvider;

import dagger.Module;
import dagger.Provides;

@Module
class UriHandlerModule {

    @Provides(type = Provides.Type.MAP)
    @UriHandlerKey(DumbContentProvider.ID_FOO)
    public UriHandler provideFoo() {
        return new FooUriHandler();
    }

    @Provides(type = Provides.Type.MAP)
    @UriHandlerKey(DumbContentProvider.ID_BAR)
    public UriHandler provideBar() {
        return new BarUriHandler();
    }
}

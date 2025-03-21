package selenify.core.decorators.modifiers;

import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;

@FunctionalInterface
public interface ProxyRequestModifier {
	HttpResponse modify(HttpRequest request, HttpMessageContents contents, HttpMessageInfo messageInfo);
}

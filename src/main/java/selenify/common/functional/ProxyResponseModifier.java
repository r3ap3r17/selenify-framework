package selenify.common.functional;

import io.netty.handler.codec.http.HttpResponse;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;

@FunctionalInterface
public interface ProxyResponseModifier {
	void modify(HttpResponse request, HttpMessageContents contents, HttpMessageInfo messageInfo);
}
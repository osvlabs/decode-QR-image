#import <Cordova/CDVPlugin.h>

@interface DecodeQRImage : CDVPlugin

- (void)decode:(CDVInvokedUrlCommand*)command;
@end
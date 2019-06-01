#import "DecodeQRImage.h"
#import <Cordova/CDVPluginResult.h>

@implementation DecodeQRImage

- (void)decode:(CDVInvokedUrlCommand*)command {
    NSString * callbackId = command.callbackId;
    NSString * imageUri = (NSString *)[command argumentAtIndex:0];
    UIImage *image = [UIImage imageWithContentsOfFile:imageUri];
    CIDetector *detector = [CIDetector detectorOfType:CIDetectorTypeQRCode context:nil options:@{ CIDetectorAccuracy : CIDetectorAccuracyHigh }];
    
    NSArray *features = [detector featuresInImage:[CIImage imageWithCGImage:image.CGImage]];
    CDVPluginResult * pluginResult;
    if (features.count > 0) {
         CIQRCodeFeature *feature = [features objectAtIndex:0];
        NSString *scannedResult = feature.messageString;
        pluginResult =[CDVPluginResult resultWithStatus : CDVCommandStatus_OK messageAsString : scannedResult];
    } else {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"No result"];
    }

    [self.commandDelegate sendPluginResult : pluginResult callbackId : callbackId];
}
@end

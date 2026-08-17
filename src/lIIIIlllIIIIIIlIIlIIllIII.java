/*
 * décompilé avec cfr 0.152 puis patché à la main
 *
 * patch : l'url de cape ligne 35 venait de strings10[877] qui décrypte en
 * "http://189cosmetics.offlinecheatbreaker.com:81/capes/" le proxy cosmetics
 * "offline" perso de ce build pas le vrai serveur optifine
 * ce proxy est mort depuis des années (vérifié : timeout alors que le vrai endpoint
 * en dessous répond nickel) d'où les capes qui chargeaient jamais pour personne
 * même ceux qui en avaient uploadé une
 * remplacé par le vrai endpoint optifine standard
 *
 * liste "could not load" d'origine via cfr :
 *  IIIllIllIlllIIllIIIIIIllI
 *  IIlIlIIIIlIlllIlIlIIIIIlI
 *  generated.Strings1
 *  generated.Strings10
 *  it.unimi.dsi.fastutil.objects.Object2ReferenceOpenCustomHashMap$EntryIterator
 *  lIIIIIIllIIIIIIIlllllllII
 *  lIIIIlllIlIIllIIIllllIIII
 *  lIIlIllIlIIllIIllIlIlllIl
 *  llIIllIIlllIlllllIlIIllIl
 *  llIlIlIllIllIllIIIIIIIIlI
 *  llllIllIIIIIIlIIIIIllllII
 *  lllllIIIlIIIIlllllIIIIlII
 *  org.apache.commons.io.FilenameUtils
 */
import generated.Strings1;
import generated.Strings10;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import org.apache.commons.io.FilenameUtils;

public class lIIIIlllIIIIIIlIIlIIllIII {

    public static void lllIllIllIlIIIlllIIllllII(lIIIIlllIlIIllIIIllllIIII lIIIIlllIlIIllIIIllllIIII2) {
        String string = lIIIIlllIlIIllIIIllllIIII2.j_();
        if (string != null && !string.isEmpty() && !string.contains(Strings1.strings[0x4D5F & 0x357])) {
            Object object;
            String string2 = "http://s.optifine.net/capes/" + string + Strings1.strings[0x612E & 0x9EF];
            String string3 = FilenameUtils.getBaseName((String)string2);
            lIIlIllIlIIllIIllIlIlllIl lIIlIllIlIIllIIllIlIlllIl2 = new lIIlIllIlIIllIIllIlIlllIl(Strings10.strings[0xFFFFE3EE & 0xB6E] + string3);
            llIIllIIlllIlllllIlIIllIl llIIllIIlllIlllllIlIIllIl2 = lIIIIIIllIIIIIIIlllllllII.IIIIlllIIIlIlIlIIIIIlllIl().lIIIlIIIIIIIlIIIllIIIlIII();
            llIlIlIllIllIllIIIIIIIIlI llIlIlIllIllIllIIIIIIIIlI2 = llIIllIIlllIlllllIlIIllIl2.llIIlIlIllIlIIIllIllllIlI(lIIlIllIlIIllIIllIlIlllIl2);
            if (llIlIlIllIllIllIIIIIIIIlI2 != null && llIlIlIllIllIllIIIIIIIIlI2 instanceof IIlIlIIIIlIlllIlIlIIIIIlI) {
                object = (IIlIlIIIIlIlllIlIlIIIIIlI)llIlIlIllIllIllIIIIIIIIlI2;
                if (((IIlIlIIIIlIlllIlIlIIIIIlI)object).lllIllIllIlIIIlllIIllllII != null) {
                    if (((IIlIlIIIIlIlllIlIlIIIIIlI)object).lllIllIllIlIIIlllIIllllII.booleanValue()) {
                        lIIIIlllIlIIllIIIllllIIII2.lllIllIllIlIIIlllIIllllII(lIIlIllIlIIllIIllIlIlllIl2);
                    }
                    return;
                }
            }
            object = new lllllIIIlIIIIlllllIIIIlII(lIIIIlllIlIIllIIIllllIIII2, lIIlIllIlIIllIIllIlIlllIl2);
            IIlIlIIIIlIlllIlIlIIIIIlI iIlIlIIIIlIlllIlIlIIIIIlI2 = new IIlIlIIIIlIlllIlIlIIIIIlI((File)null, string2, (lIIlIllIlIIllIIllIlIlllIl)null, (IIIllIllIlllIIllIIIIIIllI)object);
            llIIllIIlllIlllllIlIIllIl2.lllIllIllIlIIIlllIIllllII(lIIlIllIlIIllIIllIlIlllIl2, (llIlIlIllIllIllIIIIIIIIlI)iIlIlIIIIlIlllIlIlIIIIIlI2);
        }
    }

    public static BufferedImage lllIllIllIlIIIlllIIllllII(BufferedImage bufferedImage) {
        int n;
        int n2 = 0x270 & 0x2848;
        int n3 = bufferedImage.getWidth();
        int n4 = bufferedImage.getHeight();
        for (n = 0xFFFF9460 & 0x134; n2 < n3 || n < n4; n2 *= 0x6822 & 0x41B, n *= 0xB & 0x45D2) {
        }
        BufferedImage bufferedImage2 = new BufferedImage(n2, n, 0x1082 & 0xFFFFCA07);
        Graphics graphics = bufferedImage2.getGraphics();
        graphics.drawImage(bufferedImage, 0xFFFF80C4 & 0x528, 0xFFFF8388 & 0x6C41, null);
        graphics.dispose();
        return bufferedImage2;
    }
}

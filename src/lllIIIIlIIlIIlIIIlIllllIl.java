/*
 * décompilé avec cfr 0.152 puis patché à la main
 *
 * patch : module "hypixel mods" -- désactivé complètement à la demande de
 * l'utilisateur (lag important) le module gardait une "levelhead" (niveau
 * hypixel affiché au dessus des joueurs) qui refaisait du rendu 3d/texte pour
 * chaque joueur visible à chaque frame plus un thread de fond qui tournait en
 * boucle infinie pour interroger une api externe (hypixelcache) et ça peu
 * importe si le module était activé ou pas dans le menu (le thread démarrait
 * direct dans le constructeur) plus un handler de chat qui scannait chaque
 * message reçu (auto_friend/auto_tip/auto_gg/anti_gg)
 *
 * fix : supprimé complètement les 3 handlers d'évènements (chat x2 + entity
 * render) leur enregistrement dans le constructeur et le thread de fond --
 * le module reste visible dans le menu mods et garde ses options pour pas
 * casser la sauvegarde de config existante mais ne fait plus rien peu importe
 * l'état du toggle
 */
import com.google.common.collect.ImmutableList;
import generated.Strings10;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class lllIIIIlIIlIIlIIIlIllllIl
extends IIIIIIllllllIIIIlIlIIlIlI {
    public llIlIlIIIIlIIIlIIIIIlllII lIIllIIlIIIllIlIIllIIlIll;
    public Set llIIIIIIlllIlIIlIlIIllIII = Collections.emptySet();
    public llIlIlIIIIlIIIlIIIIIlllII lllIlIlllIIlIlIIlIlllIIlI;
    public llIlIlIIIIlIIIlIIIIIlllII llIIIllIIlIIIlIllIllIIlII;
    public Map IllIIllIlIlIlIlllIlIlIllI = new ConcurrentHashMap();
    public long llIllllllIllllllllIllIIll;
    public llIlIlIIIIlIIIlIIIIIlllII lIIIlIIIlIllIlllIIIIIlIlI;
    public llIlIlIIIIlIIIlIIIIIlllII lIllllIIllIllllllIllIIIll;
    public llIlIlIIIIlIIIlIIIIIlllII IlIllllIllllllllIIIlIIlII;

    public lllIIIIlIIlIIlIIIlIllllIl() {
        super((0x4045 & 0xFFFFA081) != 0);
    }

    public llIlIlIIIIlIIIlIIIIIlllII IIIlIllIlIIlIlIIIlIlIlIll() {
        return this.lIllllIIllIllllllIllIIIll;
    }

    @Override
    public IIIIlIlIllIlIIllIIlIlIIII llIIIllIIlIIIlIllIllIIlII() {
        return new IIIIlIlIllIlIIllIIlIlIIII(Strings10.strings[0x23D7 & 0x3CF], Strings10.strings[0x7DE & 0x73C7], ImmutableList.of(lllllIIIIlIlIIIIlIIIIIIll.lllIllIllIlIIIlllIIllllII, lllllIIIIlIlIIIIlIIIIIIll.IllllIllIIIlllIIllllllIII));
    }

    @Override
    public void lllIllIllIlIIIlllIIllllII(float f, float f2, float f3, float f4, float f5, float f6, boolean bl) {
        this.lllIllIllIlIIIlllIIllllII(Strings10.strings[0xFFFFA3D6 & 0xBEE], f, f2, f3, f4);
    }

    public llIlIlIIIIlIIIlIIIIIlllII llIllllllIllllllllIllIIll() {
        return this.llIIIllIIlIIIlIllIllIIlII;
    }

    public llIlIlIIIIlIIIlIIIIIlllII lllllIIIlIIllllllllIlllIl() {
        return this.lIIIlIIIlIllIlllIIIIIlIlI;
    }

    public llIlIlIIIIlIIIlIIIIIlllII lllIllIllIIIIllIIlIIlIlll() {
        return this.IlIllllIllllllllIIIlIIlII;
    }

    @Override
    public List IIIlIlIIlllllIIIlllIllIll() {
        this.llIIIllIIlIIIlIllIllIIlII = new llIlIlIIIIlIIIlIIIIIlllII(Strings10.strings[0x23BA & 0x53FE], Strings10.strings[0x53BF & 0x23FB], (0x2006 & 0x1508) != 0);
        this.lIIIlIIIlIllIlllIIIIIlIlI = new llIlIlIIIIlIIIlIIIIIlllII(Strings10.strings[0xFFFFE3FD & 0x7BE], Strings10.strings[0x33BF & 0x4BFD], (0x2228 & 0xFFFF8584) != 0);
        this.lIllllIIllIllllllIllIIIll = new llIlIlIIIIlIIIlIIIIIlllII(Strings10.strings[0x4BBE & 0x3FE], Strings10.strings[0xFFFF8BBF & 0x53BF], (0x6620 & 0xFFFF9810) != 0);
        this.lIIllIIlIIIllIlIIllIIlIll = new llIlIlIIIIlIIIlIIIIIlllII(Strings10.strings[0xFFFF83E8 & 0x43C6], Strings10.strings[0x7CB & 0xFFFFA3E1], (0x132D & 0xFFFFE010) != 0);
        this.lllIlIlllIIlIlIIlIlllIIlI = new llIlIlIIIIlIIIlIIIIIlllII(Strings10.strings[0x4BC2 & 0x3D2], Strings10.strings[0x63C7 & 0x7CB], (0x23E1 & 0xFFFF980C) != 0);
        this.IlIllllIllllllllIIIlIIlII = new llIlIlIIIIlIIIlIIIIIlllII(Strings10.strings[0xFFFF83CC & 0x17C5], Strings10.strings[0x3C5 & 0x27CF], (0x4290 & 0x400) != 0);
        return ImmutableList.of(this.llIIIllIIlIIIlIllIllIIlII, this.lIIIlIIIlIllIlllIIIIIlIlI, this.lIllllIIllIllllllIllIIIll, this.lIIllIIlIIIllIlIIllIIlIll, this.lllIlIlllIIlIlIIlIlllIIlI, this.IlIllllIllllllllIIIlIIlII);
    }

    public llIlIlIIIIlIIIlIIIIIlllII IIlIIIIIIlllllllllIIIIIII() {
        return this.lIIllIIlIIIllIlIIllIIlIll;
    }

    public Set llllIllIllIlIlIlIIIlIllll() {
        return this.llIIIIIIlllIlIIlIlIIllIII;
    }

    public Map lIlIIllIlIlIIlIlllIIllIII() {
        return this.IllIIllIlIlIlIlllIlIlIllI;
    }

    public long IIIIlllIIIlIlIlIIIIIlllIl() {
        return this.llIllllllIllllllllIllIIll;
    }

    public llIlIlIIIIlIIIlIIIIIlllII lllIIllllIIlIIIlIIIIllIlI() {
        return this.lllIlIlllIIlIlIIlIlllIIlI;
    }
}

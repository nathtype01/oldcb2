/*
 * décompilé avec cfr 0.152 puis patché à la main
 *
 * patch : chaque appel optionset.valueof(optionspec) / optionspec.value(optionset)
 * en dessous passe par cbargfix au lieu d'appeler jopt-simple direct
 * passer une vraie session de lancement (--accesstoken --version etc) faisait que
 * l'optionset de jopt-simple gueulait "multiple arguments" pour la première option
 * demandée même si la ligne de commande la fournissait qu'une fois
 * confirmé empiriquement (les args du process arrivent nickel même un seul flag
 * tout seul trigger ça) mais jamais trouvé la vraie cause derrière
 * cbargfix.safevalueof/safevalue reviennent à la dernière valeur enregistrée au
 * lieu de crash quand ça arrive équivalent fonctionnellement à une option normale
 * à valeur unique donc change rien au comportement dans le cas qui marche déjà
 * voir cbargfix.java
 */
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.authlib.properties.PropertyMap;
import generated.Strings0;
import generated.Strings1;
import generated.Strings2;
import generated.Strings3;
import generated.Strings4;
import generated.Strings7;
import generated.Strings9;
import java.io.File;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import joptsimple.ArgumentAcceptingOptionSpec;
import joptsimple.NonOptionArgumentSpec;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;

public class llIIlIIIlllIllllIlIllIIlI {

    public static void lllIllIllIlIIIlllIIllllII(String[] stringArray) {
        System.setProperty(Strings9.strings[0x2367 & 0x356], Strings1.strings[0x19FF & 0x1B7]);
        OptionParser optionParser = new OptionParser();
        optionParser.allowsUnrecognizedOptions();
        optionParser.accepts(Strings9.strings[0xFFFF83C7 & 0x1B47]);
        optionParser.accepts(Strings1.strings[0x61E3 & 0x9EB]);
        optionParser.accepts(Strings9.strings[0x27CD & 0x5378]);
        ArgumentAcceptingOptionSpec argumentAcceptingOptionSpec = optionParser.accepts(Strings0.strings[0x53EF & 0xFFFFA7FF]).withRequiredArg();
        ArgumentAcceptingOptionSpec argumentAcceptingOptionSpec2 = optionParser.accepts(Strings1.strings[0x31F1 & 0x2F1]).withRequiredArg().ofType(Integer.class).defaultsTo(0x63DF & 0x6FDD, new Integer[0x49 & 0x5026]);
        ArgumentAcceptingOptionSpec argumentAcceptingOptionSpec3 = optionParser.accepts(Strings9.strings[0xF4B & 0x1359]).withRequiredArg().ofType(File.class).defaultsTo(new File(Strings0.strings[0x46F4 & 0xFFFFA1BE]), new File[0xEA4 & 0x4001]);
        ArgumentAcceptingOptionSpec argumentAcceptingOptionSpec4 = optionParser.accepts(Strings9.strings[0x23DA & 0xFFFF834F]).withRequiredArg().ofType(File.class);
        ArgumentAcceptingOptionSpec argumentAcceptingOptionSpec5 = optionParser.accepts(Strings9.strings[0x4B6B & 0xFFFF83CF]).withRequiredArg().ofType(File.class);
        ArgumentAcceptingOptionSpec argumentAcceptingOptionSpec6 = optionParser.accepts(Strings9.strings[0x23CC & 0x436F]).withRequiredArg();
        ArgumentAcceptingOptionSpec argumentAcceptingOptionSpec7 = optionParser.accepts(Strings9.strings[0x3CD & 0x334D]).withRequiredArg().defaultsTo(Strings9.strings[0x75E & 0x5B4E], new String[0x114 & 0xFFFFC221]).ofType(Integer.class);
        ArgumentAcceptingOptionSpec argumentAcceptingOptionSpec8 = optionParser.accepts(Strings9.strings[0xFFFFC34F & 0x3CF]).withRequiredArg();
        ArgumentAcceptingOptionSpec argumentAcceptingOptionSpec9 = optionParser.accepts(Strings9.strings[0x6358 & 0xFFFF87D0]).withRequiredArg();
        ArgumentAcceptingOptionSpec argumentAcceptingOptionSpec10 = optionParser.accepts(Strings3.strings[0xFFFFA4BD & 0x9B8]).withRequiredArg().defaultsTo(Strings4.strings[0xFFFF9376 & 0x64E7] + lIIIIIIllIIIIIIIlllllllII.lIlllIIIlIIlIIIlIlIIIIlII() % (0xCAF3D2C7B0A10BEBL & 0x14683E8L), new String[0x1049 & 0x4002]);
        ArgumentAcceptingOptionSpec argumentAcceptingOptionSpec11 = optionParser.accepts(Strings7.strings[0x9BE & 0x71B6]).withRequiredArg();
        ArgumentAcceptingOptionSpec argumentAcceptingOptionSpec12 = optionParser.accepts(Strings9.strings[0xFFFF83D7 & 0x1351]).withRequiredArg().required();
        ArgumentAcceptingOptionSpec argumentAcceptingOptionSpec13 = optionParser.accepts(Strings0.strings[0x45FB & 0x9FF]).withRequiredArg().required();
        ArgumentAcceptingOptionSpec argumentAcceptingOptionSpec14 = optionParser.accepts(Strings2.strings[0xFFFFA7E4 & 0x3EB]).withRequiredArg().ofType(Integer.class).defaultsTo(0xB56 & 0xFFFF9756, new Integer[0xA83 & 0x7028]);
        ArgumentAcceptingOptionSpec argumentAcceptingOptionSpec15 = optionParser.accepts(Strings2.strings[0x43E3 & 0x13ED]).withRequiredArg().ofType(Integer.class).defaultsTo(0x1E8 & 0x11E2, new Integer[0xFFFF8081 & 0x6040]);
        ArgumentAcceptingOptionSpec argumentAcceptingOptionSpec16 = optionParser.accepts(Strings9.strings[0x1BDB & 0xFFFF8352]).withRequiredArg().defaultsTo(Strings0.strings[0x9EC & 0x157D], new String[0xFFFF8542 & 0x303C]);
        ArgumentAcceptingOptionSpec argumentAcceptingOptionSpec17 = optionParser.accepts(Strings9.strings[0x753 & 0xFFFF93DB]).withRequiredArg().defaultsTo(Strings0.strings[0x217C & 0x13ED], new String[0x126 & 0x5C88]);
        ArgumentAcceptingOptionSpec argumentAcceptingOptionSpec18 = optionParser.accepts(Strings9.strings[0xB7D & 0xFFFF9754]).withRequiredArg();
        ArgumentAcceptingOptionSpec argumentAcceptingOptionSpec19 = optionParser.accepts(Strings9.strings[0x355 & 0xF5D]).withRequiredArg().defaultsTo(Strings3.strings[0x19DF & 0x4197], new String[0x420 & 0x691D]);
        NonOptionArgumentSpec nonOptionArgumentSpec = optionParser.nonOptions();
        OptionSet optionSet = optionParser.parse(stringArray);
        List list = optionSet.valuesOf((OptionSpec)nonOptionArgumentSpec);
        if (!list.isEmpty()) {
            System.out.println(Strings9.strings[0x37E & 0xFFFF83D6] + list);
        }
        String string = (String)CBArgFix.safeValueOf(optionSet, (OptionSpec)argumentAcceptingOptionSpec6);
        Proxy proxy = Proxy.NO_PROXY;
        if (string != null) {
            try {
                proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(string, (int)((Integer)CBArgFix.safeValueOf(optionSet, (OptionSpec)argumentAcceptingOptionSpec7))));
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        String string2 = (String)CBArgFix.safeValueOf(optionSet, (OptionSpec)argumentAcceptingOptionSpec8);
        String string3 = (String)CBArgFix.safeValueOf(optionSet, (OptionSpec)argumentAcceptingOptionSpec9);
        if (!proxy.equals(Proxy.NO_PROXY) && llIIlIIIlllIllllIlIllIIlI.lllIllIllIlIIIlllIIllllII(string2) && llIIlIIIlllIllllIlIllIIlI.lllIllIllIlIIIlllIIllllII(string3)) {
            Authenticator.setDefault((Authenticator)new llllIlIllIllllIlIlllIllII(string2, string3));
        }
        int n = (Integer)CBArgFix.safeValueOf(optionSet, (OptionSpec)argumentAcceptingOptionSpec14);
        int n2 = (Integer)CBArgFix.safeValueOf(optionSet, (OptionSpec)argumentAcceptingOptionSpec15);
        boolean bl = optionSet.has(Strings1.strings[0x59FB & 0xFFFF81E3]);
        boolean bl2 = optionSet.has(Strings9.strings[0x358 & 0xFFFF8368]);
        boolean bl3 = optionSet.has(Strings9.strings[0x347 & 0x7DF]);
        String string4 = (String)CBArgFix.safeValueOf(optionSet, (OptionSpec)argumentAcceptingOptionSpec13);
        Gson gson = new GsonBuilder().registerTypeAdapter(PropertyMap.class, (Object)new PropertyMap.Serializer()).create();
        PropertyMap propertyMap = (PropertyMap)gson.fromJson((String)CBArgFix.safeValueOf(optionSet, (OptionSpec)argumentAcceptingOptionSpec16), PropertyMap.class);
        PropertyMap propertyMap2 = (PropertyMap)gson.fromJson((String)CBArgFix.safeValueOf(optionSet, (OptionSpec)argumentAcceptingOptionSpec17), PropertyMap.class);
        File file = (File)CBArgFix.safeValueOf(optionSet, (OptionSpec)argumentAcceptingOptionSpec3);
        File file2 = optionSet.has((OptionSpec)argumentAcceptingOptionSpec4) ? (File)CBArgFix.safeValueOf(optionSet, (OptionSpec)argumentAcceptingOptionSpec4) : new File(file, Strings1.strings[0xF7 & 0x27F7]);
        File file3 = optionSet.has((OptionSpec)argumentAcceptingOptionSpec5) ? (File)CBArgFix.safeValueOf(optionSet, (OptionSpec)argumentAcceptingOptionSpec5) : new File(file, Strings9.strings[0x437F & 0xB57]);
        String string5 = optionSet.has((OptionSpec)argumentAcceptingOptionSpec11) ? (String)CBArgFix.safeValue(argumentAcceptingOptionSpec11, optionSet) : (String)CBArgFix.safeValue(argumentAcceptingOptionSpec10, optionSet);
        String string6 = optionSet.has((OptionSpec)argumentAcceptingOptionSpec18) ? (String)CBArgFix.safeValue(argumentAcceptingOptionSpec18, optionSet) : null;
        String string7 = (String)CBArgFix.safeValueOf(optionSet, (OptionSpec)argumentAcceptingOptionSpec);
        Integer n3 = (Integer)CBArgFix.safeValueOf(optionSet, (OptionSpec)argumentAcceptingOptionSpec2);
        lIlllIIllIllIlIlIlllIlIlI lIlllIIllIllIlIlIlllIlIlI2 = new lIlllIIllIllIlIlIlllIlIlI((String)CBArgFix.safeValue(argumentAcceptingOptionSpec10, optionSet), string5, (String)CBArgFix.safeValue(argumentAcceptingOptionSpec12, optionSet), (String)CBArgFix.safeValue(argumentAcceptingOptionSpec19, optionSet));
        lIlIIlIIIIIlllIIIlIlIllIl lIlIIlIIIIIlllIIIlIlIllIl2 = new lIlIIlIIIIIlllIIIlIlIllIl(new IIIlIllIlllIlllIIIlllIllI(lIlllIIllIllIlIlIlllIlIlI2, propertyMap, propertyMap2, proxy), new IIlllIIlIlllllIlllIllIlIl(n, n2, bl, bl2), new llllIllIlIIlIlllllIIIllII(file, file3, file2, string6), new IlIlllIlIllIIIllIIIIIIIII(bl3, string4), new IlIIlIlIlIIIllllIIllIIIII(string7, n3.intValue()));
        Runtime.getRuntime().addShutdownHook((Thread)new lllllllllIIIIIIIlllIIlllI(Strings9.strings[0xFFFFC75E & 0x33F8]));
        Thread.currentThread().setName(Strings9.strings[0xFFFFC75D & 0x359]);
        new lIIIIIIllIIIIIIIlllllllII(lIlIIlIIIIIlllIIIlIlIllIl2).lllIllIllIlIIIlllIIllllII();
    }

    public static boolean lllIllIllIlIIIlllIIllllII(String string) {
        return (string != null && !string.isEmpty() ? 0xFFFFC21D & 1 : 0x2000 & 0xFFFFC844) != 0;
    }
}

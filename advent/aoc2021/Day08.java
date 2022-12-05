package advent.aoc2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day08 implements IDay {

	String input = "begfcd fabg aecgbdf cefagb edgcba eacbf efgbc bca ab decfa | cbgef befdcg bceaf fagb\r\n"
			+ "cfdegb bdefca ac becdf cefagd bfac cfegbad aegbd cad beacd | ca dgfaec gbdae agcdfe\r\n"
			+ "dfegbc dea cabdefg efdgca fgabed dgeac cgadb gfdec ae afec | dfecg feca bgcad ebgfcd\r\n"
			+ "cdbag ca gdbae cga acfd cabdefg bgfcad eabcgf defcgb bcdfg | cagbd gadcb gacbd dbagfc\r\n"
			+ "facedg begfac efdga ag cefadbg gacd bdgefc afg fdbea fegdc | fag ebcgfd gfeda afg\r\n"
			+ "cb cab cefad dafecb caefb cfdb cdfgabe dgebac afgdec fagbe | bdceaf fcdb deacf cb\r\n"
			+ "cbaged adefcgb bdcf gaedbf gdcef fgbed bgdfec ecgaf cde cd | gbdfe cd efgbcd cdbf\r\n"
			+ "dcfag gebc acgef cfe cdbefag cfbaed ec begfa efcagb egdabf | bfage ce ec gdfca\r\n"
			+ "geabf befcdag dfbg fegac bg adbgef deafb bagced fadceb gbe | dgcaefb bdgf cfeag gbe\r\n"
			+ "fc acdeb ecbdf edafcb bfdge acdebg gbcfea cdfa abefcdg bfc | decab egcabd dacgeb fagbdec\r\n"
			+ "dfg bfga faedb dfeag dcbgef dgfecab faedbc fg bedafg egdac | eagdbf daefg gdf gcdebfa\r\n"
			+ "acdgfbe afebdg agebc efcdab dbcafg dc efcd cabed abfde dca | ecfbda dgbecfa adc begdfa\r\n"
			+ "fbadge dfaecb dcfbg acgbef cgadefb egda feadb dbfeg ge gfe | bgfdcae efgbd fgecadb feg\r\n"
			+ "bfgdace abdc ebdgac aecgf cbg afbegd acegb fgcbed bc dbgea | cb cebdga gbc eabgd\r\n"
			+ "baef gedafc ba cfead bdcgf gefacdb cadgbe abc bfcad bfadce | gcefad agedfc bfae cafde\r\n"
			+ "cadfb fdbge gbcefa adcfbg bae ebacfdg ae bedfa cdea eadcbf | edgfb dfgbcea ae deca\r\n"
			+ "cfbadeg fcdeg cagebf gac gcaed bgad acefdb edabc baedgc ga | acg eacdbfg gdba badfce\r\n"
			+ "fda bagdcf da ceagdfb ecgfa beagfd fdgae egbdf dbfcge deba | bacefdg dbea bfegd dgbfca\r\n"
			+ "aceg dcafg eaf cdgefa ebadfc adefg dfecbga dcbgaf ea bedfg | daefg fegad agce fcebagd\r\n"
			+ "aged gd dgbfca egbac dcebf dgc ebdcag cegdb begacf bcegfad | afecbg bcadeg gecfbda edga\r\n"
			+ "cfbaeg bfca bae fdbge cegaf ba eagfb gfcdae agbecd gbecfda | cbgefa ba aeb cbaf\r\n"
			+ "ag dgafceb gfacdb gcdaf gac abfdc facegb beacdf bdag cdfeg | adcfgbe bgcfea ga cbeadf\r\n"
			+ "cgfdb gfbdce cebg dfgaeb dgefac cg dgbef cbdfega fdcba gdc | degfab fcdgea fgcbd cg\r\n"
			+ "afecg fca dgcef cdgeba af abgce dcfbae bafg cfbeag egdcbfa | gecdf aebcg dgacbef cabeg\r\n"
			+ "dbcea gadef bfge bg dbg adcfge dbgfcae abdge bgfdea bfcagd | bgd gcadfe abged fcbdaeg\r\n"
			+ "gfaecbd ab cfeba abfd acb fabcde ecbgf eagdfc adecf cdageb | fbaec ba cgbdeaf ab\r\n"
			+ "bfedg egafb fcae gbdace bfcegad bgafdc bgfca ega cfbeag ea | cfadebg cgbdaf efac bcgfa\r\n"
			+ "dfbgeca cfbega aedcfb faebgd feabd aeg gbde eg cdgaf dafge | bfeacg gaefd ega bcafge\r\n"
			+ "bacd agc fgdbc fdgcab ac bgfca aegbf adcgbef ebcfgd eadgcf | cbdafg agc dafbgec bgcfad\r\n"
			+ "gc cfg ebcafd edacgf fgdcb afdgb fcebd bgce aebcgdf fgbcde | cgbe cbge gc bgce\r\n"
			+ "feadc fg dbcgae fga bcefag fbdcaeg gacef febg gafbcd gaecb | fg afg afg gf\r\n"
			+ "cfea ce gbdefc bdacf agfdbc badfce bgdea adbce gcfbaed ebc | ce bacdf dcabf ec\r\n"
			+ "dafcb beagfc abcgf fedabgc fda ad dcga gfbcda egdbfa ebfcd | agdc eafgbdc ceagfb daf\r\n"
			+ "ecbagd edb gafed caebgfd gcdafb cfdbg bedgf cefb ebgfcd be | bgdfcae adcgeb deb ecgabfd\r\n"
			+ "aebdfc dba bafdgec adfcg dbacf ab dgfeab befcd bedgfc acbe | ecba cadfbe fgcebd gfcbde\r\n"
			+ "dc gdc cfaeg gdbcfae edfbcg cagbfd dgacf cbda abfdge abgdf | cbad efagc dabfg fedbga\r\n"
			+ "acefbg gda ad agdeb edfcag abcdge bfdcgea bdgfe bdca agebc | ad bcgfeda ad gda\r\n"
			+ "gd gbeacd fdbgac bfgea eagcbdf fagbd gbd cfabed fcdg fdcba | fcdbga gdafb cdgaefb bfgad\r\n"
			+ "ef bfegcda bfe bgfea dacgfb cebga gfdab feda egfdba fgedbc | fe ebgaf dfea gbeac\r\n"
			+ "afbge ecdbag gdfaec cafbe badefgc adbfge dgeba gf dgbf gfa | bdfg fga feagb fg\r\n"
			+ "afcedb cgabe ecd beafd cbfd dc edabc bgdafe gfcbdae acgedf | dec debfa gabec dfeab\r\n"
			+ "gcafd fb bdagcf cebfga fgdb bfc cegfdba dcabe dfbac acdgef | cagebf dfaegc agfcbd feadgc\r\n"
			+ "aefcb geab ga eacfg gca cbefad gefcadb dfbcga fgdce baecgf | ga bgae bdefgca deabfc\r\n"
			+ "gfdc dcbfga fg edafbgc acbdeg dabef fcaegb gdcba fbdag gfb | baefd bgf feabd bfg\r\n"
			+ "gdbcea ef edafc agdce facbd gcfead gfbeca dfge fec cafegdb | bfcda facged adfcebg fce\r\n"
			+ "bgadc aedg efgacdb abdfc gdb dfcegb abecg efgbac gd acgdbe | adbgc dgb afcdb gdae\r\n"
			+ "deg adbcfge dcbeg ceag dfbace bdaecg bdcgf bcdae bdfgea ge | adbec eacg ge gde\r\n"
			+ "fbgde afdgeb bfgecd dgfa aegbf fabedgc ag ecbaf gab ecbgda | agb bfgde gedfb eabgf\r\n"
			+ "dfega gedcfb bdagf fgcdae bdg ebad cafbg gbfcdae gbaefd db | adbgfce bdgafe ecdabgf gadbef\r\n"
			+ "cbagf adgeb df dfbe gdabcfe bedfag dgf fcgeda abfgd ecbgad | fd dfbe cfdbgae gedbfa\r\n"
			+ "fabdecg cdgae cdaebg acdef gecdbf egacfd dfc fd fgda bfcae | dbeacg feadc gfad fd\r\n"
			+ "cegdabf db cfeadb gdcef adbg gfabc bdfcg agdcbf dfb facgbe | dfb dgfce acdbegf cbfgad\r\n"
			+ "gfecab bedgcf ecgbdaf cagbf dgba dg gacfd cfeda gfdbac cgd | bagd fecgbd begfcd dgc\r\n"
			+ "dfcagb fdabe daebc af adegcb afce fegcadb dbfge edafcb fad | cgebda cdfbae bfgdac fa\r\n"
			+ "ec gedafc fcbegad dabcef aecb dfabe dbefag gcdbf cfedb edc | fgdaeb bace ceab eacb\r\n"
			+ "faegcd bce bdcgae gcabe acdb bacfdeg daegc bc efcbdg bgfae | ceb bcgea bc bec\r\n"
			+ "gcdfe bacdf be bdefc ebd adbfce gdeabc bafe cabdfg edbcfag | bgcfda dafcb gdbfeca fcdbga\r\n"
			+ "agdfce defcgab fbdgc ba fgabd baf bdae gefda gedfab gcaebf | ba cgefab fgbdc aefgcd\r\n"
			+ "fbcdag egcdba cgfed baedcf afbg cdagbef bf cbf agbdc bfcdg | gbfa egbcad fb defgc\r\n"
			+ "gafc dcg bgefcad fdbacg cg gcdab bfgda gbfade cebad defbgc | bacde fbdga cdg facg\r\n"
			+ "dfgbca bfed abgefd becag dgface bgf abgfe bdcfgae fb defag | gfb cgaeb bceag fb\r\n"
			+ "efadc gcfbaed cdeafb cabdge acgf geadfc afdge gdebf ag aeg | aegbcd afcg edabcg fadgec\r\n"
			+ "abcdg daebc ae afgdcbe cbefdg dbfec afeb eca cabfde gfecad | ceabd cdgab bedca dbfgcea\r\n"
			+ "gedbafc gdbef edg abefcg cbfgd edgbaf afdcge gaefb ebad ed | bfdge bgfae ed abedfg\r\n"
			+ "cgabdfe ebc degbc debcgf acgbd bfdge adfbge efbgca ce dcfe | acbefdg begadf fbdge dfce\r\n"
			+ "fecgb adbe cbage fgcead gdcea cgedafb bgafcd abc bedacg ba | fgbdac dfaceg gdeafcb acb\r\n"
			+ "edfag abdf befgd bf ebgdaf cdebg gbfdeac fgb bfgcae egfdac | ecfgab fbg gbf ecdagf\r\n"
			+ "cdebg dace ebgcdfa cagfbd ebdcag ce gedbf ecg bacgfe cagbd | acfgeb ec ceg gabcef\r\n"
			+ "defca edgca gecafb adg gd bedg dbgace agbec cdafebg abgdfc | gedb degb ebcafg fcade\r\n"
			+ "gcabedf cdb dcegb dbegf cb acedg fcgbad fabedg fcdebg bfec | dgbef cdage cbdeg bdcge\r\n"
			+ "dae bfgdea ad fcbedg aegdc gedfc cegfadb dfaecg bcega afdc | aed dcebfga beacgfd beagdf\r\n"
			+ "fge cegfdba cdgae fdgcba ef bdgfa acgebf dfaeg afgdbe fedb | ef gecda afdgb fagbdc\r\n"
			+ "afd cfgd bdgeca fbega bdgaf gfebdca badgcf gcabd cbfade fd | afd gdabf cfdaeb dacgeb\r\n"
			+ "ebgca dg bfcgdae gdb bedfc geda dgafbc cbged dbcgea cabfge | gd adfcgb daeg gd\r\n"
			+ "gd facbge bagced fdcbe dbg gacd cebga ebdfga becdg agedbfc | cadg dagbef ebfdc cadg\r\n"
			+ "adf dcaef cfbage bcefgad feacgd df gedf gcfabd ecgaf ebdac | ecabd dfaec afdec cdaeb\r\n"
			+ "gaf adgef bfae fa dgacbf aebgd cgadbe gecfdab efdgc aegbfd | fedgba dacbfeg gedcf bdacfg\r\n"
			+ "ebfdc cebgda cfbged fgdeb efcg fbedac fbgcdea afdbg gde ge | ecfg facdbge debfg dge\r\n"
			+ "dgef gabdc ebcagf fbcdea aefcbgd begadf dgabe ge eag baefd | efbgca cdagb degf cadebf\r\n"
			+ "dc cbgead ebcga bcd cgbaedf cgfabe agbcd dgbfa cgde acfedb | caebgd cedg cd becdfa\r\n"
			+ "gcbfae bca fgac gadbce fdeabg ca edbfc cfeadgb fgabe acebf | facg fgabe caebdgf fcag\r\n"
			+ "fagdbe afcge fadcgeb aecdgf cfegb fegad afc edca ca fbgdca | fgdacb eadgf bcdfgae cfabedg\r\n"
			+ "ebgfca bgfec gfb efdbc aegbc adecgb dfabegc fg gafe fgabdc | bedcf efag gecba gbfec\r\n"
			+ "dgabef caef bdfcae fe bgdfc fbe dfecb abgdce cbdefag beacd | fbe cdabfe cbedf cefa\r\n"
			+ "bgcfda facbe gdeacf cd dcgb bdfeagc dca dbgfea cdbfa gfadb | fbcda fbeca cd bfgad\r\n"
			+ "bfcgda bcgdfea facbe aecgdb badge cg beafdg gecd agc gcaeb | gefbda bedga edgcab gced\r\n"
			+ "gac gacef fdabgc fbdcega abfec cg cebfga eadfg fbecda cgbe | bcfaed fbeca gc ebfgac\r\n"
			+ "gd badcg fdbac bfgd eafdgcb gda eacbg bfadce gbdfca gfecda | fcdabe gd bcfad cfeagbd\r\n"
			+ "da bdga beagdfc fcgbda cefbda fgcda fcedbg gcfbd egfca dac | afbgcd da abgcedf badcfe\r\n"
			+ "aec decbfa ecgb faecg bcfeag adcfbeg ec fgaed bdgfca bafgc | gacbef bdcfea gafec fabegc\r\n"
			+ "geafb abdge gfb cfab efgca cedfgb fb feadcg cegdabf fgaebc | efbgac bfg bf fb\r\n"
			+ "bdcf abfcge dacfg gcdab fc fcagedb fcdagb gcf gdaecb gdeaf | fcagd fcg cbfgaed gdacb\r\n"
			+ "egbfcda bfgdec efd acef ef dgace edfagc bgfda agfde dceagb | gaedc dcbgfe degca fcae\r\n"
			+ "cbdeagf gbfad defgbc cbfea dbegfa dacbf cd cbgfad bcd dcag | bdcaf becdgf dafgb bdgfa\r\n"
			+ "edcfa gae bfga cegabd caebgf cbgfe ga fcgdaeb efgdcb facge | efcbdg cabegd bfgec ag\r\n"
			+ "eagfdcb cgab gadbec adc ebdafg edcba ac deabg bfedc dcfega | ac cgba adc edcba\r\n"
			+ "degbc ae dabfgc dcgaf gafdce ega acedgbf bagecf agdec aedf | edcbg gcebaf abgdfc defa\r\n"
			+ "dbgaf gd gdcfae fcabd dgbfcea abefg facgeb dag edfagb egbd | fgbdae gbfdea bgafd fcgbade\r\n"
			+ "cf dcbga gbfeca cedgba cfg edgfbac cbfgd fcda gdbef dbgfac | fgc cgdba cfbega edbcag\r\n"
			+ "fd cgbead fcd befgdc bcdge becdaf cbegfda fcdbg gfde gacfb | cdf fgcba gbced df\r\n"
			+ "cdgafb dgfe cgefb cebfdg bfg cgbde fg acfeb fcdgbea gcebad | dgef cfbeg gf fbg\r\n"
			+ "gebcfa fecdba fcgeabd eg eafdc caegd dacbg cgfade cge fegd | eagcd efgd dabcfe cge\r\n"
			+ "geacd gcbadf abedgc cgfabde fdec aefdg gbfae daf ecgadf df | adgefc adf dfa gedaf\r\n"
			+ "dbea de bfgdcae cdgbf def ceafb ceabgf ecfdag feabdc efbcd | ed gbcfd cedbf cfdbae\r\n"
			+ "dgaef dac bacdfe cefad cd acdbeg cefgba cdbf eabcf fbgaecd | cd eafcd dc acd\r\n"
			+ "bagdec gabec egfc cbfgae dgefab abdcf bfe aefbc ef ecfbgda | dafecbg fbegad fdabc efb\r\n"
			+ "bagfec bfegc aefdgbc dfagcb gef ge dceafg abeg bfdec gfcab | ge dfcabg cabgf adcegf\r\n"
			+ "fbadcg fegad cadeb cg fcgade gacde edgfab aecgfdb cdg cgfe | fdagbe gdeacfb gecf facgde\r\n"
			+ "gcebf edcg fcgdbea feadbc dgfabe cfe gbfde cfagb dbcegf ec | fbegd cef bcdefa faebgd\r\n"
			+ "dacbe gecadf dcefg adf bedgcaf cdfgbe af dgacbf fdeca aegf | agefcd gdaefc dgefbc fcdge\r\n"
			+ "gedfca abgdce bagde fabg efgbd fdgbea gef bcfegda ebdfc gf | fg defabg ebcfd fg\r\n"
			+ "ad gcfea cadeg adc degfbc gdabec cfadgb eabcgdf dbgce ebda | cegaf acd efacbdg gbdcae\r\n"
			+ "dcbgef bdgecfa fgce bgdafe dbefc fed fe gdacbf cdaeb bfgdc | acfdgb fe fcbgd gcfe\r\n"
			+ "fad da dgecf edgbaf adfbgc gfebac adbfgce badc bafgc cagdf | da acfgdb da fdecg\r\n"
			+ "fbdag adefcgb bafdgc ae gbade gcdeb gdebfa eba abegcf afde | aedf defagb cagdbf bae\r\n"
			+ "cfgaeb cbf fc adgbce agbdfc gbcda egfbd gcfbd fdac ecfbgda | fc gcdebfa cdaf adcf\r\n"
			+ "cgabd dgc eadcfbg facgde acbefd bcafg gd ecbgda bgde edbac | dabcg bfagc ebdac ceadb\r\n"
			+ "fbdca cfedba def cgdea fadcgeb ef dbfgac ebfagd cefb defac | dbcaf cbfe efd abgdecf\r\n"
			+ "abfe ea gcafd fcgdbea gae eacgdb gfbce efacg cebgfd ecfgba | cadfg agcebf fabe bfceag\r\n"
			+ "edgc fabgecd fabdc eabgcf deafcg eca gedbfa ce efgad faedc | fcdae debagf bfadc dfcea\r\n"
			+ "dbcef cdbaf cagbde ecadbf aefb gdfec fbdceag acdbgf be deb | dcgfe cabfgd ebaf dcafb\r\n"
			+ "bdaeg gebaf ecdfga gda cbdea ebfgad bacdefg acebfg gd bfdg | aefgb gda bdfg gbecdaf\r\n"
			+ "beg efdgc fbged gfaedbc edfgba bagfd cfegba cdgafb eb bead | deab egbdfac ebfcgda baed\r\n"
			+ "eba fgea becgfda fcbead cbegfa abegc ecgfb dabgc cfbdge ae | afbceg eba feag aeb\r\n"
			+ "dcgbfa degba debfa egcafbd adgcbe eag abcdg fagbec cegd ge | gcde bdeacg dagbe dbfea\r\n"
			+ "egcdba cdaegf abfc dagbf ab cedbfga fdbge cdfga gba abdgfc | fgdeca bag bag afcb\r\n"
			+ "bagdc fbea abfdegc deb acdefg ebdac fecda deafcb ecgfdb eb | fcgead be dfbcge dbeca\r\n"
			+ "abdfe gebcfd gedab adcfgb abf debcf af adbfegc fdacbe face | af fcebd cdabfg baf\r\n"
			+ "fdbega dfg eadcg cfgde bcefd fdeacb fecdbg fg fbgc cbagdfe | cdgef adebcf ebcgfd fegdc\r\n"
			+ "fgadecb fagec fa gcdbef adfc baecg gdbafe afe afegcd dfcge | ecgdaf egbcdaf cabeg edgbcf\r\n"
			+ "fcegba egabf gaedb acgfbde bad bdgf db fdaebg bfdeac cgead | dab agcbfe gcdea caefbd\r\n"
			+ "egbfa fgbc egbafc ecb bcgdfea bagdef cb acdfe gedbac cfabe | bfgc cb afbgec fgcedab\r\n"
			+ "ec fadcbe dcgaf fegab fgeacb egbc eac fcebdga fbdaeg cgefa | abfeg ebfag bagcef aec\r\n"
			+ "baegdf agbcdfe dagce abcg dca daegb dcgbea ac degcf dacfbe | fbeagd adcbge adegc abecfd\r\n"
			+ "ebac bedafc fab cadfe bgfcd debfga ab dcgafe gdfbace bcafd | afgdbce ba ebcfad aebc\r\n"
			+ "cfdabg acfedb edgbf acgf acdgeb fad cbadg adgfb fa eacgfbd | gaefbcd fgac fad agfc\r\n"
			+ "fage ged dgaec bcgad gecadfb decfa badfec dcfbge ge acfdge | dgbca fbcegd bcedfg adegc\r\n"
			+ "fcgdb cebadf cdgab fbecgd bfacedg eabdgf fgec fecdb fgd gf | fcge dabgfe dgbfc ecfg\r\n"
			+ "eafgc cfedab bagdecf bg bcgeda cebdf dbgf geb gbecf bcedgf | gfcae cfbeg egfbc bfgd\r\n"
			+ "adefgb efdgbc fcdbgae gdfbe dafb fgaecd fga gbeca bgeaf fa | dcgfbe edgbf fa ebgaf\r\n"
			+ "afgcb afdce gacfd cfeagd dcg dg gfcbde egda abcfde bagfecd | dbfaceg dfeac cdg gfbac\r\n"
			+ "degf cde febcda cdfga afdgec cebag gdaec cgdbaef gadcbf de | fged abfcde dec cfgad\r\n"
			+ "bdfcae dgaef gfdab bfgdec aecfgdb edfcg ae egfcad aef caeg | gefad fea ae fcaedb\r\n"
			+ "dcbgfa bagcd badce ed gbadcfe aecbf decbgf gbdeac dec aged | bedca gbaced adcbe cdegabf\r\n"
			+ "cgfbed cgb bc gfabd cfgabd gaefcbd egfdba fcba cbdag ecdag | fabc gcaedfb agefdb bc\r\n"
			+ "abcfgd becafgd ecdb ce febga ebafc bfdca fec edfbca facegd | dabegcf dafcgb gaecbfd ebfac\r\n"
			+ "beacf gdbfaec bgfd dba db fagecd dbafeg ebadf ecdbga dfega | gacebd ecgadf bd gdbfae\r\n"
			+ "decg dafebg cfd gfaebcd becdf bedgf begfcd cd gdafcb fbeca | cfdgba dacbgf fabedg bcfea\r\n"
			+ "acbedg bgeac edc bdefa cd ecadb beacfg gacd ebdfcg edcabfg | gbdcfe cd adbec ebgca\r\n"
			+ "gadceb ebfgdc ebcfg efacb fg fgdeabc bcadfg fgde debcg bfg | bfeac facbgde fg dcegbf\r\n"
			+ "edfc cadbg eagfd cge adgec gdcfea ebfagd ec fegcab fedbcga | fced cdfe ec ce\r\n"
			+ "defacgb acgfbd gafd agebcf bdcfg gcbda dcabe gefbdc ag gac | ga gdfa cga ag\r\n"
			+ "cedgbf fgabe dcbfg efgacd ed bdcfga bcde def geacfdb dgbef | fde cdabgf gaefcd ceafdg\r\n"
			+ "ce gcdeaf cdabe fdebcga acgbd fadeb gdfcba dbgcea ecgb cea | ec afebd dfcaeg adebc\r\n"
			+ "bgaced agdce adgcf dfa beafdg fd bgfac faecdg acbegfd decf | edagcb gbfdcea eabdcg adf\r\n"
			+ "fce fbcg geafbc bceaf egfba gcdefa cf afbedg baced dgaebcf | fec aefbc dgaefc cbade\r\n"
			+ "aefg baegfd cfdbag adbge ag bdcea adg cgefdb bafdecg febdg | fage dbace bdgacf cbdfga\r\n"
			+ "bgfeda bface caefg debfac adgcbf acdebfg bdcaf ebcd feb eb | ecdb fcgae bcadfg ecdb\r\n"
			+ "edcabf gebfdc ecdbf ba edafg acbd cfeagb fab bfaed gbacdfe | dcab ba gfaed fab\r\n"
			+ "dafg dcafgbe da acebgd gbefda abefd facgeb eda fedbc egbaf | dae cedbgaf bdgaec fbced\r\n"
			+ "fcae gbcdf edabfcg edf ecdbfa badce abdegc fbdeag ef cefbd | def efd efd feca\r\n"
			+ "ecf cagfb edgfcb gbaedc cgebd cdbfea fe dagecfb gdef fegcb | gfed cdbefa gefd ebgcd\r\n"
			+ "decafgb fed dbgaf cegbd ebfcgd gdecaf cfbe ebdgf fe bagdec | fed agdcbe ebcdg eadfgcb\r\n"
			+ "bfced befagc ca gbdefc cab ecad afbcd abgdf afcebgd fdeabc | fdbag ca bacfd ca\r\n"
			+ "ecadbg adbge fae dfcaegb dfebc gbefca af afgd badfge feabd | fa bdcefag cafebg efbdc\r\n"
			+ "afdge agfdbe agdcf bdgecf cg bdagfce fedgac eacg cbdfa cgf | defga bcafd cg fdgac\r\n"
			+ "bgfcde ecf bdecaf beca fecad adebf dcafg gbeadf decabfg ec | dabef cef adfce cdbeaf\r\n"
			+ "gcabf gbeda febdacg gbdfca fdg gfcdea fd fabgd febcag dbcf | bcfd df dfcb bcdf\r\n"
			+ "dgfae feg cgfbdae debagc gedca feac degcbf fe agfdb cdfeag | egadcb eagdbcf dgecab daebcg\r\n"
			+ "gbfc fdebgca fb cedaf gcebd dcfbeg dfbega bdf edfcb agdbec | fbd cbgf edcbg bfd\r\n"
			+ "gdea dcgbf afbed deafbc fgdaceb daegfb abcfeg ag bgfad agb | edag efgacb bga aegfdb\r\n"
			+ "dagbcf efbda cfbde fcd afdbgec ebcgf eagdbf dc bdcfea adec | dc fcgbaed acfbde dfbce\r\n"
			+ "acfbd efad gafcdb adceb ea aecgbf cgedb abecdgf aec efdacb | bdafc bcfad befcgda ea\r\n"
			+ "gdaebc cbage af afgc bdcafe ebfdg bfa fgeba fagbdec cgefba | ecgbaf egcafb abf gcfa\r\n"
			+ "cbfdae agebf afb geafc edcgfa cfgb ebafcg adgeb ebcfgad bf | efagbc afcdbe bgdea bfadec\r\n"
			+ "beadcgf gdc eadcb bcfga bgacd fcebdg fdga fgacbd gd gfbaec | adcbg agdf cbagf cabdg\r\n"
			+ "gba cdba cbegaf gbdec gefcdb gdfceba gbead ab geafd cgadbe | ba dbca deafg efagd\r\n"
			+ "cfgeab bagedf ceabd dfeabcg aedfbc acged dba db bcdf bceaf | db db dba beacf\r\n"
			+ "de debg dgcef cbdgaf cfbedg bcdgf cfaeg dfe bceafgd acbfde | fcgea cdgefab gfabdc ecfdab\r\n"
			+ "febacg aecb bgfedca bfagde cdgfb fgbce ebg be efcga dcegaf | dgeafb ceba cfeabg cbae\r\n"
			+ "aegbd gdafbc dae gadcb fadgcbe fagedc adbgce ea ceba fgbed | gdfeac cbae ceab dcbfeag\r\n"
			+ "df fgabde gfd ecgda cdgfeb bcdf cefgb cbefga abefgdc gdfec | aedgfb fd fdg dfg\r\n"
			+ "fbade gefab df cgfedb dfb fadg beafcg bcegdfa cbade agefdb | fbead bgfea dafg fdag\r\n"
			+ "ac edfcabg fcgde adc eafdb ceafdb fbac dcafe decgba edbgfa | ca ecbdaf facb ecdbfa\r\n"
			+ "fgb fgebac gbcaf fdaecgb fgae ecafbd cabdg fg fbeca cegdbf | dbaecf bdfcge dgefacb fage\r\n"
			+ "dfea gacdbe ad agfbed dfgba gfebca bgfcd dag geafb dbfeagc | gdafecb debgcfa ecfabdg gafebc\r\n"
			+ "cefgbad efd fgceb beafdc fdaegb gacdfb dafcb befcd ed cdea | befcg ecda ed fcebg\r\n"
			+ "fgcea eb adbcfg cbfgd efbdcag feb fdgecb efdgba dbec bgcef | edbc egafbd feb fbdage\r\n"
			+ "cfbda gfbdaec cfaed bceagf gabd cab dgcfb dgfcba ab fcdbge | cafbge fedac ba gbcdfa\r\n"
			+ "ecdafb cegfd edcgfa efd faeg gcdbe cegfbda ef afgbcd afcdg | adfcg ef aegf fgedc\r\n"
			+ "cagfbe egafdbc cfa aebdgc cdfbag gfda af dbacf bdecf cgabd | gfda bgdefac acbgd acbdf\r\n"
			+ "bfcde egafc afdg cbaged dgecfa cfabge dcg bagefcd dgcef gd | cagef efbcga dcg cedfga\r\n"
			+ "aed gcafebd daefc cgdaef aecgf efbdc abefgc da gdac febgad | agdc eda dagbfec ceagbf\r\n"
			+ "befad cfgaebd dcefab adcfge ec cgbfd beca bdefc gdebfa ced | cabe beac cebfd edfcb\r\n"
			+ "acbd cea deafbc edbaf ecgadf gbfec dbgefa eafcb ac bgedfac | abegdf fgcbe efcab ac\r\n"
			+ "gf fadce fag bcgad bcfg fdcga abdfeg gdfacb degbca fgdbace | cdaegb feacd fbgc eafdcgb\r\n"
			+ "ebgdca fedgab fdcab acdegfb cabdfe fda acbde af bfgcd ecaf | bfgeda deabc bdeca ecadb\r\n"
			+ "fbedcg cf cabdg dgcebaf cfg efcb aegfcd fbdcg fdabeg fegdb | ebcf fc gbfcd cdfbge\r\n"
			+ "def fd fedga ebdcaf fgbd cbgeafd cabdeg edfbga gdeba agfec | cagdbe fbgaecd afgce fde\r\n"
			+ "cefba bafde fbgdec dagb dae gfdeab geafcd ebfgd eabdcfg da | aefbd eda adebgf da";
	
	@Override
	public String part1() {
		int unique = 0;
		for(String s : input.split("\r\n")) {
			for(String t : s.split(" \\| ")[1].split(" ")) {
				if(t.length() == 2 || t.length() == 3 || t.length() == 4 || t.length() == 7) {
					unique++;
				}
			}
		}
	
		return Integer.toString(unique);
	}

	@Override
	public String part2() {
		int total = 0;
		
		for(String line : input.split("\r\n")) {
			String[] parts = line.split(" \\| ");
			//keep hashmap of arrangement for each number
			HashMap<Integer,HashSet<Character>> poss = new HashMap<Integer,HashSet<Character>>();
			
			ArrayList<String> testCases = new ArrayList<String>(Arrays.asList(parts[0].split(" ")));
			
			//first, find one, four, seven, and eight - easy
			poss.put(1,setFromString(findOfLength(testCases,2).get(0)));
			poss.put(4, setFromString(findOfLength(testCases,4).get(0)));
			poss.put(7, setFromString(findOfLength(testCases,3).get(0)));
			poss.put(8, setFromString(findOfLength(testCases,7).get(0)));
			
			//find three - three is only size 5 that contains all of 1
			for(String s : findOfLength(testCases,5)) {
				HashSet<Character> test = setFromString(s);
				if(test.containsAll(poss.get(1))) {
					poss.put(3, test);
					break;
				}
			}
			
			//find nine - only 6 that contains all of 4
			for(String s : findOfLength(testCases,6)) {
				HashSet<Character> test = setFromString(s);
				if(test.containsAll(poss.get(4))) {
					poss.put(9, test);
					break;
				}
			}
			
			//find zero - only 6 that contains all of 1 and is not 9
			//can also find six - only 6 that does not contain 1
			for(String s : findOfLength(testCases,6)) {
				HashSet<Character> test = setFromString(s);
				if(test.equals(poss.get(9)))
					continue;
				if(test.containsAll(poss.get(1))) {
					poss.put(0, test);
				} else {
					poss.put(6, test);
				}
			}
			
			//similarly, five is only 5 that *six contains all of*
			//and two is only 5 that is not three and that six does not contain all of
			for(String s : findOfLength(testCases,5)) {
				HashSet<Character> test = setFromString(s);
				if(test.equals(poss.get(3)))
					continue;
				if(poss.get(6).containsAll(test)) {
					poss.put(5, test);
				} else {
					poss.put(2, test);
				}
			}
			
			//string is the simplest way for left-to-right digit appending
			String num = "";
			for(String s : parts[1].split(" ")) {
				//unfortunately, must iterate over entire map
				HashSet<Character> a = setFromString(s);
				for(int i : poss.keySet()) {
					if(poss.get(i).equals(a)) {
						num += i;
						break;
					}
				}
			}
			
			total += Integer.parseInt(num);
		}
		
		return Integer.toString(total);
	}
		
	public List<String> findOfLength(ArrayList<String> a, int length) {
		return a.stream().filter(x -> x.length() == length).collect(Collectors.toList());
	}
	
	
	//primitive type conversion is a bitch
	public HashSet<Character> setFromString(String s) {
		HashSet<Character> a = new HashSet<Character>();
		for(char c : s.toCharArray())
			a.add(c);
		return a;
	}

	public static void main(String[] args) {
		DayRunner.run(new Day08());
	}

}

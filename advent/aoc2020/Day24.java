package advent.aoc2020;

import java.util.ArrayList;
import java.util.HashSet;

import advent.utilities.general.Coord3;
import advent.utilities.general.DayRunner;
import advent.utilities.general.IDay;

public class Day24 implements IDay {

	String input = "seseseseseswsesesenwseseseswenweeese\r\n"
			+ "nwneneseswneneweeneneeeeneeneene\r\n"
			+ "swswseswswswswswseseeswswswswseswsesww\r\n"
			+ "nwsenwnenenwnwnwewnwnwnwnwnwnwwnwnwnw\r\n"
			+ "sesewseesesenwnewewwnewseeseseswsene\r\n"
			+ "eneneneeewswnenewsenenwnwnenewnene\r\n"
			+ "neneneswseseeeswsw\r\n"
			+ "newneeeeneeeeeeeene\r\n"
			+ "wnwwwswwwnwewnwwsewenwwwwnww\r\n"
			+ "swewswswseseseswseewseseseneswnewwsesw\r\n"
			+ "nwswnwsenwnenwwnwnwnwwnwwnwnwnwwnwnwnw\r\n"
			+ "swwwwwnewwwwswswwwwwwseswsww\r\n"
			+ "wwwewwwwswwwwwwwwwswsw\r\n"
			+ "wnwnwnwnwnwnewwnwwnwnwnwsewswwnwww\r\n"
			+ "sweswnwwwnwwnwnwnwseenwnwnwnwnenwnwnw\r\n"
			+ "nenwwwsewnenwwwwwwnwwnwwswnwnww\r\n"
			+ "eeneeesweeesenwneneeeenweeeene\r\n"
			+ "enwwswnwwnwnweswnwnwseswnwnwnwsewnenenw\r\n"
			+ "nwnwnwnwnwswnwnwnwnwnwnwnwnwnesenwnwnwnenw\r\n"
			+ "nwswwsewswwwwww\r\n"
			+ "sesweseeneeswnweeseweseswsesenwnw\r\n"
			+ "sewswwnwneeswswnewswswswwwwnwswwse\r\n"
			+ "enwnwswnwnenwnenwswneneneneneswnwsewnenwnw\r\n"
			+ "nwnenwnwsenwnwnwnwnwwwnwnwnwnwnwnwnwnw\r\n"
			+ "seesesesesesewsesesesesesesesesesesesene\r\n"
			+ "swneneseswsewswwsenesesewswwnewswseswne\r\n"
			+ "swnwswsenesewneswswnwswsewewswseswnene\r\n"
			+ "wwswwseseswwnwnww\r\n"
			+ "esewnwseseswnweseesenwswseesesesee\r\n"
			+ "seeneneneseenenwsweeeeeeeewe\r\n"
			+ "seseswseswsewseseswsweseswseseswswsese\r\n"
			+ "swneeneeneseneweesewne\r\n"
			+ "nenenenenenenenenenenwnenwsenenenenenene\r\n"
			+ "neneneswneneneneneneswnenenenwenwnenene\r\n"
			+ "wewwewwwnwswswnwewwwwwwwsw\r\n"
			+ "seseseenwsweeeesenweneeeseewse\r\n"
			+ "neneneneenenenenwnenenenewnewnwnwnesenw\r\n"
			+ "nwnwnwwewwseswnesewnwswwseswenene\r\n"
			+ "wwswswswswswwswswsweswswsw\r\n"
			+ "nwnewnwwsenwnwnwnwwnwwnwsenwwwnwwsenw\r\n"
			+ "swweeenwseeseseeenwweseeseseeese\r\n"
			+ "nwnwnwnenwnwnenwwnwnwnwnwnwnwnwnwnwnwe\r\n"
			+ "nwnwnwnwnenwnenenenwenwnwnwnwswnwswnwnw\r\n"
			+ "wwwswwewwwwwwwwwnwwwwwsw\r\n"
			+ "neneneneneeneeneenenenesw\r\n"
			+ "nwswneeneeneseweneeweneneswnwswneswse\r\n"
			+ "seeswwenwneeneeeneeswneeswsenwe\r\n"
			+ "weewwwwwswwwnewwwwnwwwsww\r\n"
			+ "wneseewneweswneneneneneneeswnenesene\r\n"
			+ "swnwenwenwseswneswewnwwnwwsenwnwe\r\n"
			+ "wsewnenenenenenenenenenwnesesenenenene\r\n"
			+ "nenwneneneeneneneswnenenewneenenenenene\r\n"
			+ "eswswswwswnwwswswsweswnwswswswwswe\r\n"
			+ "seeseseseseeeswsewnwneseseesesesese\r\n"
			+ "eseenwseseesweeeeseeweeeesee\r\n"
			+ "eenewneeneneeneeneeswneneneswsene\r\n"
			+ "nwnwsenwnenwnwnwnwnwnwswnwnwnenwnwnwnwnwne\r\n"
			+ "wnwwnweeseswnwnwsweesenewnwnwnwnw\r\n"
			+ "nesweneenenwnwneneswneneseewene\r\n"
			+ "nenenenenenenenenenenwsenenenwneneneeswnenw\r\n"
			+ "swswsweswseswswseswseswswswnwnwsenwswsee\r\n"
			+ "wwnewwwwwsewwewsewnwswwnwnew\r\n"
			+ "swswswseswswswswswseswswsewsweswseswsw\r\n"
			+ "eeneenesweeeeeeeeeneenweee\r\n"
			+ "nwwwwwwnewnwwwwswwww\r\n"
			+ "nenwnwneeneswnwnwnwswnenenwnewswnwswnw\r\n"
			+ "eseseenwsesesesesewsesesesesesesesesese\r\n"
			+ "wwnwwnwwnwenwwnwnwnwnwnwnwsenwswnw\r\n"
			+ "sesewseseeeneewesenwseeseenesee\r\n"
			+ "seeesewsesewwneeeeeseeeeenenw\r\n"
			+ "wswswswswswswwswwwswewwwswwwsw\r\n"
			+ "eswwswswwwwswswseswwwnewwwwwwsw\r\n"
			+ "weseswseeswneseswwsewswseeewnenwsw\r\n"
			+ "nwnwnwnwnwnwenenwnwenwnenwnwenwnwwswsw\r\n"
			+ "senwnenweesesewwesweeeseneneew\r\n"
			+ "nwwnenenenwneeneneneswneneswwsewseene\r\n"
			+ "wswwwewswwewsw\r\n"
			+ "eswneeeeeeeeswneeneeeneenwe\r\n"
			+ "nwseeswnwsesweseseseseseeneswneseseese\r\n"
			+ "eeseesenweeee\r\n"
			+ "senwnwsenwnenwnwnewnenwnwsenenwnwnwswnw\r\n"
			+ "neenwsesewnwnewnwnenesenenwnewseswnene\r\n"
			+ "eweeenwesweeeeeeeseeswneee\r\n"
			+ "eeeswnweeeeneeeeeweeeeee\r\n"
			+ "nwnwnenenenwneneswnwnwnenenenwnenwnesenw\r\n"
			+ "eeweeeeeseseeeeeeeeeee\r\n"
			+ "wswswwsenwwnwneewesewswwnewnew\r\n"
			+ "nenesenesenwnenenwswwnwnenenenenenenee\r\n"
			+ "nenenwenwnwnwnwswnenwneneswnwne\r\n"
			+ "wwswswswwswwwneswwnewww\r\n"
			+ "enwnenwnwwnwnwnweswnwnwnwnwnwswnwnwnene\r\n"
			+ "seseswswswseseswseseseswsesenwseswseswe\r\n"
			+ "newnenwseneeeneneswnenenenenenenwseenee\r\n"
			+ "eeswseeswsesenweeenwsewsee\r\n"
			+ "wnwnwenwnwwnwnwnwwnwnwwnwnwnwwnwnw\r\n"
			+ "nwnwnwnwnenwnwnwnwnwnwewnwnwsenw\r\n"
			+ "swswnwsweswswswswswswswswswseswswswsw\r\n"
			+ "swswwswswswwswswswswswwswwswswweesw\r\n"
			+ "swswswesweswnwswwsenwseswswewnwswswswne\r\n"
			+ "wwwnwwwwewwwwwwwwwwew\r\n"
			+ "wwnwwwwwnwwwwwnwwnwsewnenwnw\r\n"
			+ "nwnwnenwnwnwenwnwswesenwnwswseneneww\r\n"
			+ "newseseesewseweenesesweneewswe\r\n"
			+ "wenwwewswnwwnwnwwwnwenenwsw\r\n"
			+ "neeeeneneneeeenwneswswneswneenesw\r\n"
			+ "seeseseseseswseseeseneseseswnwnwsesewese\r\n"
			+ "eeeeweneeenweenenweeesesesesw\r\n"
			+ "eswnenwneeneenesenenwneseneenwswnenenew\r\n"
			+ "nenwnwwnwnwnwnwenwnwnwnwswnwneneneenw\r\n"
			+ "nwnwnwnwnwnenwnwneenenwnwnwwnenwnenenw\r\n"
			+ "eeeseeeeeeeseweeeewsee\r\n"
			+ "wnwwnwwnwwwwwwnwsenenwnwwwnwww\r\n"
			+ "eseseneesesewnwesweneneswsenweewsw\r\n"
			+ "nwnwnwenwnwwnwnwnwswnweenwnwnwnwnwnww\r\n"
			+ "nenenenwswnenwnenenenwneenenenesenenenene\r\n"
			+ "neeswnwnenenenenenenwneneneneneneeneswne\r\n"
			+ "swseenenwneeneeeeseneenwnenenenee\r\n"
			+ "neswnwnenenenenwnwnwseewnwnenwnwnenene\r\n"
			+ "senwseswsweseseseseswsesenwnwseseneseenw\r\n"
			+ "eneeneneeneswswewneneeswneenwnenw\r\n"
			+ "neenwwneswswsesenesewwsesesesenwwe\r\n"
			+ "seseneseewseseeesewseseseswnwsesene\r\n"
			+ "sesenesesesesesewswseeeseseseeesese\r\n"
			+ "wnenenwnenesenenenenenwneneewnenwsesenw\r\n"
			+ "sesesesesesesenwseseneseseseseseswseswsese\r\n"
			+ "sweswneswswswswswswswswswswswswwswwswsw\r\n"
			+ "nwsweswnwnweseneswsesenewnenwswneenew\r\n"
			+ "neeneneenesweeseenenenewneesweeene\r\n"
			+ "neneeenewwseewneseeeseneenwee\r\n"
			+ "seseseseseeeeeeeseweeseseese\r\n"
			+ "enwswneeeneeseneeneeweeeeenesew\r\n"
			+ "eswswswswswswwnwseseswseeswnwseswswsw\r\n"
			+ "nwewswswwswswswnesewseswswnesweswsee\r\n"
			+ "nwnwenwnwwsenwnwnwnenwnenenwnenwnwnwnwsw\r\n"
			+ "eneneneneneneenenewneneneeneneneene\r\n"
			+ "nwnwnenwnenenwnenwsenwnwnwnwnenenewnwswnenw\r\n"
			+ "wwwnwwswnenewswwseswwwnenewsew\r\n"
			+ "ewwswneenenenwswwnwewenenenewese\r\n"
			+ "nwenwnwnwneseseswnwnwnwswswwneswnwnenwne\r\n"
			+ "nenwenwsenwnwneswnwswnenenwnewnwnwenwnw\r\n"
			+ "wnwnwenwnwwnwnweneswnwnwnwswnwnwnwnwnw\r\n"
			+ "eneeswneenewneenenenewsenwnenee\r\n"
			+ "enwnwnwnwnwnesenwnewnwne\r\n"
			+ "nwwnwwnwnweenwsenenwwwsenwwwswne\r\n"
			+ "wwwnwsweewneswweeswweswsewnw\r\n"
			+ "swseeswwneswseswwswseneswnwswswswwswswsw\r\n"
			+ "enewenweswsesesesewswneeenwwwse\r\n"
			+ "senwnenwnwnwnwnwnwnwnwnwnwnwnwnwnwwnwnw\r\n"
			+ "wsewnenesenwenwswnwnenwswnwnwnwwenwe\r\n"
			+ "wnwwnewwnewwnwnwsewnwnwsweswww\r\n"
			+ "seswswseseseeswnwnwsenweeswnwseswsesese\r\n"
			+ "nwnwwnwneseweenenwnwnenwswneeswseswne\r\n"
			+ "swnwnwswswswswswswswseswseeseseswswsese\r\n"
			+ "nenwnenenwsenenenewnwnwnwnwnenenesenenw\r\n"
			+ "swnwwwwesewweewnwwesenwnwnwnw\r\n"
			+ "swwswseneswwneswsenenenwswneseswswsenenesw\r\n"
			+ "nenenenenenwenesenenenenewnenwnwnenenwne\r\n"
			+ "swnwwswwwswswwswwswwwwseeswsww\r\n"
			+ "eseseseeseseseweseseseseseweee\r\n"
			+ "swsweswswswseswweswnenwswenwwswswne\r\n"
			+ "nwnwnwnwnwnwnesenwnwnwnenenwnenenwnwnene\r\n"
			+ "neswwwwwnewwewseweswwwswww\r\n"
			+ "seseswseneseeseswnwseswseswseswseseswse\r\n"
			+ "sweeeeeweseeeeenwenw\r\n"
			+ "swnwnweewwnwsenwsenwwnewswnenwnwsw\r\n"
			+ "eweeeneenwwseneneswnesewnwseesenwne\r\n"
			+ "swswswswnewwwswswsewwwnwwswweswsw\r\n"
			+ "nwnesenenwewsenwwneseseneswnenenesenw\r\n"
			+ "ewnwenenwseseeneweeesewsw\r\n"
			+ "eeeenwenenweeenweseeneseeseee\r\n"
			+ "esenwseesesesesesweee\r\n"
			+ "sesesenwseswnwswsenwseseenesewsewnwnw\r\n"
			+ "wnwwwwwwnwswwwwewwwwwwww\r\n"
			+ "nwnwsenwnwnwnewnwnwnwnwnwnwwenwnwnwnw\r\n"
			+ "wsewwswwneswwswswwnewswswwwnese\r\n"
			+ "nwnwnwnenenenwnwnwenwwnenwnwnwnwnenenw\r\n"
			+ "nwnwnwnwewnwwnwnwwnwnwwnwnwnwenwnw\r\n"
			+ "wswwswswsewnwswwswsewwwswwswnew\r\n"
			+ "eeewseeeeseneeweeeeeeee\r\n"
			+ "nenenenenenenenenenenenenenenenenenenesw\r\n"
			+ "nwnwnwnwenwnwnwnwneswnwnwnwseenwswwnw\r\n"
			+ "senenwswnenenenwnenewnesene\r\n"
			+ "seswseseeneseneseseeseseseeseseswese\r\n"
			+ "nenwswnwnwsenenwsenwnwsenwnwneswnwwnwenwnw\r\n"
			+ "eeweseneseseseseseeeseeese\r\n"
			+ "swewnwwswwswswwwwwwnwwsewww\r\n"
			+ "swneneneneneeswwnewswseneswneneenenenene\r\n"
			+ "eenwneeneneeseswseneenwnenenenene\r\n"
			+ "sewswsweswseswseenwsenenesese\r\n"
			+ "seswseseswseseseseseseswseseseswseseenw\r\n"
			+ "sweeeeeeeneeseeeseseeee\r\n"
			+ "seweneeswnwneeeeneneenewnesenwne\r\n"
			+ "swwswewwnwnwwnwwewwwwwwnwnwwnw\r\n"
			+ "neneneneneneneswneneneneneneneeneneswnene\r\n"
			+ "wwwnweesenwnewnwswnwnwnwwnwnwnwww\r\n"
			+ "wswnwnwnenwweswewwswswwsewswwsw\r\n"
			+ "sewseseseeeeneseeeeeeeeesee\r\n"
			+ "senenewneseenwneneneneeswnenenwnewnene\r\n"
			+ "neneneeeseneenwneneenwneseneswneeeww\r\n"
			+ "swswswswswswswswswwswswswswsweswswsesw\r\n"
			+ "neswwwswwswwwwewswswewsewswwnw\r\n"
			+ "neneneesweeneneneneneewnenenwnenenene\r\n"
			+ "eswswnenenewneseneneeswnewneesenenene\r\n"
			+ "nwnwnwnwnwnwnwwnwwwnwnwnwswnwwswenwne\r\n"
			+ "seeseesenwswnwesweseneneeeseeeee\r\n"
			+ "eeeeneneneneeneneewneeeseene\r\n"
			+ "seswwswswswswsweswwnwsweswswswswnenw\r\n"
			+ "wwwwneseswwwnwswsewswwswwwswsww\r\n"
			+ "wswnwswswswsesewwwwwswwswwneww\r\n"
			+ "wseswswewswswswswseswewswseswswesw\r\n"
			+ "swnweswwwwswswswswwswww\r\n"
			+ "wswwswwswwswswwnewwsenwwwwswew\r\n"
			+ "neseneenewswwesweneseese\r\n"
			+ "wwwsenwswsewnwnwesweenwwswwnwww\r\n"
			+ "wswnewswsewnwswwswswnewswswswswswwsew\r\n"
			+ "swwswwneseswswswww\r\n"
			+ "neneswnweseenwswneenenweeeeneenee\r\n"
			+ "eenenenwseewseesewswseeeeseeew\r\n"
			+ "enewnwwnwwnwwseneswwwwswnwwsenwnwne\r\n"
			+ "eweseewseeseneneseeseeseenwwsese\r\n"
			+ "neswwswenwwwwwnesewswneswwswsesw\r\n"
			+ "nwnenwnwnenwnwnwnwnwnwnwnwswenenwnenwnw\r\n"
			+ "swnenwnwnenwnenenwnwnwnwnwnwneewsenwnenwne\r\n"
			+ "nwneenwnwnenwnwnwnenwnenwnwnwnewnw\r\n"
			+ "swswseswswswswswswswnwseswswswneswsenwsesw\r\n"
			+ "nwnwnwnenwnenenwnwnwnwnenwswnenwnwnwnenw\r\n"
			+ "nwwnenwnwnwnenwnwsenwnwnwnenenwnwnenwne\r\n"
			+ "neeseewwswseenenw\r\n"
			+ "swwseswswswneseswswswesesenwswneswsesw\r\n"
			+ "newnwwwnwnwwwnwsenwwnwwwesenwnwnw\r\n"
			+ "eswseseswwswseneswsesesesesesenwsesesese\r\n"
			+ "eeeeeeeeeeenweeeeseeee\r\n"
			+ "eesenesewseseeseeeseseeeeseesese\r\n"
			+ "wnwnwnwnwnwenwswnwwnwnwnwnwwwswnwnwnew\r\n"
			+ "nenenwnwnwwesesenwwswnwnewswswswenw\r\n"
			+ "eeseeenwnwswswewnwsweneeenwsenee\r\n"
			+ "eseenwsesweseweeseeseseeswsesene\r\n"
			+ "sesewseneseswsesesesesesesese\r\n"
			+ "nwnenwnwnwnenwswnwenwnewnwnenenwswneene\r\n"
			+ "wwwwwswwwnwwwwwewwwwwww\r\n"
			+ "eseesewseeeseseseeseesesesenesesee\r\n"
			+ "swswewwewwewsw\r\n"
			+ "swseswnwswseeswseswswswswswswswseseswswse\r\n"
			+ "wnwnwwwewwwwwwwswnwnwsenenwww\r\n"
			+ "swswswswswswswseseneswswswswswswswswsww\r\n"
			+ "wenwwewwwswsweswswsenwwswwneew\r\n"
			+ "nwnwneeneswneneneewneswnwneneswnwswnw\r\n"
			+ "wsewswnwnwnwwenwnwnwnwnwnwnwewnwnesw\r\n"
			+ "swswswswswneseswswswswnenwse\r\n"
			+ "eswnenenwseswseseswseseseswseswneseswnw\r\n"
			+ "enenwnwsenwnwnwwneswnenwnwnwnwnwnwenw\r\n"
			+ "sewsesenesewewseseseseseneweseseewse\r\n"
			+ "sesewseswseseswseseseseseswswswseswsenese\r\n"
			+ "neesenenenwneneenenesewneeneeewnw\r\n"
			+ "senenenesenenewwneneenwneneneeenenene\r\n"
			+ "wwwwwnwwwwwnewsewwwwwww\r\n"
			+ "swseswwswswswnwswneene\r\n"
			+ "sesesesesenenwsewseswnesenwwsenenwsewne\r\n"
			+ "nwwwwwwewwww\r\n"
			+ "neneeneeeeswnenenesenwneeneneenenew\r\n"
			+ "eweeeeeeeseneeeeeeewee\r\n"
			+ "newnwnenwnwwwwwswwswwwnwwnwwwnw\r\n"
			+ "nenwnwenwnenwnenenwnenenwnwsenwnwnenew\r\n"
			+ "wswseenenwnwwneswnenwwnenenenenenee\r\n"
			+ "swswnwsweseseswswswseseswswswseswswseenw\r\n"
			+ "seseswswnwseseswwnwsweseseseseseeseswe\r\n"
			+ "nwnwnwwwnwwnwwwnewwwnwsesewnwwnw\r\n"
			+ "swswseweeswswswsewswswnwwwnwenenwsww\r\n"
			+ "eswswesesesesenweseseseseneneseewsenese\r\n"
			+ "wnwenwwwwnewwwwnwnwwwwsw\r\n"
			+ "seeeenwswwsenwsesenwswneswswwwswe\r\n"
			+ "nenesewwwswswswnwswswnewswsw\r\n"
			+ "neseneseswseeseseewswwsewswsenwswswsw\r\n"
			+ "eeseeeseweeneeeeeeeeesesee\r\n"
			+ "sesweenenenweeseeswesweseeeneww\r\n"
			+ "nwnwnenwnenwwswswnwwwse\r\n"
			+ "nenenwswneneesewenenwesenwnenenesene\r\n"
			+ "eesewnwseeneeeeeneseesewwnwe\r\n"
			+ "swenesesenesenenewwnwnenenenesw\r\n"
			+ "swswswswswswswswnwswswswswswswsweswswswsw\r\n"
			+ "eseneswseseseseseseswswsesenw\r\n"
			+ "swsweswswsesenwswswnewswswswswswswnwswsw\r\n"
			+ "wwwneswwswwwneswwsewwswwwwsw\r\n"
			+ "nwnwnwwnwnwnwwnwnwnwnwnwnwenwnwnwwnw\r\n"
			+ "wswwswwwewswswswneswswwwswwwwsw\r\n"
			+ "nenwnenwneenwnenwnenwnwswwnenenwnwnenwnw\r\n"
			+ "seeseseeseeseseweeewnenesw\r\n"
			+ "senwnwnwnwnwnwnenwnwnwnwnwnwnwwwnwsenw\r\n"
			+ "enenesenenwwesewee\r\n"
			+ "swnwswnwneseenenenwswnwnewnenwwnwneesene\r\n"
			+ "swswswseswswswswswswswswswseswswwseesw\r\n"
			+ "swwswnwwwwwwswwewe\r\n"
			+ "wswenewnesesenwswseswseeneneenenwnenwe\r\n"
			+ "wwnwswswewswewww\r\n"
			+ "seseesesesesewsenwseneseseeseseeeww\r\n"
			+ "swseswswswswswseswswseswseswsenwseswswse\r\n"
			+ "wnwwwwwsenwnwweswnwnwsenwewwsw\r\n"
			+ "nenwnwewswnenenenenwnwswnwnwnenenenenwnw\r\n"
			+ "wnwwnwnwnwenwseswnwswenwwwnwwnww\r\n"
			+ "neenwnwswnwneneneenenenwsewneswnwnenwnw\r\n"
			+ "wwswswwwwwwwswwwwnewswnewww\r\n"
			+ "wsenwswsweswewneenweswsenwneneese\r\n"
			+ "swseswnwseseswseseswseswswswseseswseenwse\r\n"
			+ "seswswseneswsesesweseseswseswneswnwwswsese\r\n"
			+ "nenwenwwnwneswnwenwnwnw\r\n"
			+ "neswewnwnwswneenwsenwswnwnenenwnwenenw\r\n"
			+ "eswswnwnwnenwwnwnwswnwnwwnenwnwnwsenw\r\n"
			+ "nwnwnenenwnwnenwswnwnwnwnwnwnenwnwnw\r\n"
			+ "wwwswwwswwwwweneeeswwwwne\r\n"
			+ "eeeenenwneneneneeneeswneeneweee\r\n"
			+ "nwnwnwnwsenwswnwnwnwnwnenwnwnwnwnwnenwnww\r\n"
			+ "swsesesesesesesesesewesesewseseseseseswne\r\n"
			+ "seseeeseeeweeeeeesesewsesee\r\n"
			+ "seswneseswneswsenwenewwsenwsenweswswene\r\n"
			+ "swswswswswswswswswswswswsweeswnwsesww\r\n"
			+ "swwswswswswneswswwswswswwswwwsw\r\n"
			+ "swnwseswswswswswwswswwswswswswswswswsw\r\n"
			+ "nwnenwnenenwswneneneneswnwneenenwnwnenwnene\r\n"
			+ "nwnwwnwnwwsenenwnwnwnwnwwnwwsenwsenw\r\n"
			+ "seseeesesesesesesesenewseeewsewse\r\n"
			+ "swswswenwneneeeenwneenwnwsenene\r\n"
			+ "seseswsewesesesesewseswsenweenwsese\r\n"
			+ "eweeeeesweeenweeeeseee\r\n"
			+ "eseswseswsenwswseswseswswswsw\r\n"
			+ "ewnwwnewnwwnewnwswswnwsenwwnwwwwe\r\n"
			+ "esesenweseseseseswseseseneseseesesenwse\r\n"
			+ "wsweswswwweswwwswswswwwewwswnw\r\n"
			+ "nwnenwnwnwneenenenwnwwnwnwnwnenwwene\r\n"
			+ "seewsweesenwnwwsee\r\n"
			+ "swneswwwswnwenwewswnenwwswseewswswe\r\n"
			+ "eeeeeeeeeeeeseeenwee\r\n"
			+ "sesweeeeseenwenw\r\n"
			+ "wswswswwswwnewseenwswnewswswswswwsw\r\n"
			+ "seswseswneswnwnenwse\r\n"
			+ "senenwswswsenwswseswsenwneeseseswseswse\r\n"
			+ "nwswswswseswneswswseswseseseeswsenesesw\r\n"
			+ "weweswwwswnwwwseswwwnewwnwwsw\r\n"
			+ "neeneeneswneeeeeeneneeneenweene\r\n"
			+ "nwnesenesenwseesewseseseseswesenwew\r\n"
			+ "sesesesesenwnweswweseeeswseneeese\r\n"
			+ "wnwwwewswwwwswewwswwwwnwwww\r\n"
			+ "wswwwswswewwnwwswswswwwswwswww\r\n"
			+ "eeeeeseseeeeneeweeseeenwee\r\n"
			+ "seeeeseeeseeeeeeesesesenwee\r\n"
			+ "seesesenwwswseseneseseswseseseseseseswsese\r\n"
			+ "newwwwswwswwwswwswweswswswsww\r\n"
			+ "nenwnwswnwnwnwnenwnwnwnenenenenwnwnenenw\r\n"
			+ "wwsewwsenwwnenewwnewsenww\r\n"
			+ "swswswswswswswswseseswneseswswswswseswenwnw\r\n"
			+ "seesenwnenewseseswswswwnwseseesesesese\r\n"
			+ "enweseneeneeseeneeeseeweewwee\r\n"
			+ "eeweeeeeeesesenewnwseseesesese\r\n"
			+ "wswsenesesenesesewseenwswswnenwswnwenenw\r\n"
			+ "eswseswesesenwewswwnewnenweswsese\r\n"
			+ "nenwnwneswnwnwnwnenenwnenenwnwnwnwswnwne\r\n"
			+ "nwnenesenenwwneewnwnwnwwnwnwnwnwsenw\r\n"
			+ "seswseswswseseswswneswswswswswswswswsw\r\n"
			+ "nenwneneenenenwswnenwneswneenwneswnenenene\r\n"
			+ "seswswseseswseseswseseneswswseswswseswse\r\n"
			+ "swswseswnenwswswseswswswswseswseswswswswsw\r\n"
			+ "wswwswswwneswswwsweswswswwswsw\r\n"
			+ "nenenesenenwnesenesenenewnwnenwnwnwswsenw\r\n"
			+ "wneenenwnenesesenewnwswnwenwneswnene\r\n"
			+ "sewenwwwwwwseenwnwnwwwnwswwnwne\r\n"
			+ "ewwneeneneeseeesewneneewnewne\r\n"
			+ "nenenesenesenwwneneswnwnweneswswsenwne\r\n"
			+ "weseeeeseeeneneweseseswsewwnw\r\n"
			+ "nwwwswwswsesewswnwswswswwnweswswe\r\n"
			+ "weswwwewwnwwswwwnwseewwwnw\r\n"
			+ "seswswseswseeseswswnwsewseswswnewswswe\r\n"
			+ "sesenwswesenwswseseseseseswswswswswswswsw\r\n"
			+ "wnwnwesewsesewwswnwnwwewnewwnee\r\n"
			+ "seseeseswwswswseswsw\r\n"
			+ "swwnwenwseneswwneenwnewnwnesewswnesee\r\n"
			+ "nwwnwnwnwsenwnwnewnwnwnw\r\n"
			+ "esesewneseeeeeeesee\r\n"
			+ "senwnesewnwnwnenwwswnwwnenesenwnwsww\r\n"
			+ "seswsewswswesewseseseseseneswswsesesw\r\n"
			+ "neseseswwenewswswswnwswsewsesesenesw\r\n"
			+ "senesenwnwswsesewseseswswseseseseswsee\r\n"
			+ "enenenenewneeeneneneneenenenesenenene\r\n"
			+ "wwwwnwnwnwwnwnwnwewwwnw\r\n"
			+ "wnwenwnwnwnwswnwsenwseswwenweenwnwsw\r\n"
			+ "nwnwnwnwenwnwnwnenewnwnwnenwnwnwnwnwnw\r\n"
			+ "seseseenwswnwswswswswseseswneneseswswwsw\r\n"
			+ "neeenwnenwwwnewnenenwnwnesenenenenw\r\n"
			+ "swsenwwnwseneesweswseneneeeneenwwswse\r\n"
			+ "eeeeeseneneeeneneneneeneweesenwe\r\n"
			+ "ewseeswneewenenweeeweneneeeene\r\n"
			+ "swswswswswseneswwswswseswseseseswseswsee\r\n"
			+ "eeeseseseseseseesenwseeeseseseswsese\r\n"
			+ "nwnenenenenewnenwnenenwneswseenenenenenenw\r\n"
			+ "swsesweseswswsenwswseseswswseseswswnwse\r\n"
			+ "senenenewenenenenenenenewnenenesene\r\n"
			+ "nesewseneswwsweseswneseseseswnesese\r\n"
			+ "nwnwnwnwnwwsenwesenwnwnwnwnwnwnwsenwnwnw\r\n"
			+ "nenwnwwnwnwnwnwnenwnwneenenenenenene\r\n"
			+ "wswwsenwwswswwewnww\r\n"
			+ "wwwnewwswwwwwwsewwwwwswww\r\n"
			+ "wswseseneswewseneswswnwswswsenesenwswe\r\n"
			+ "wnwnwenwwnwnwwswswwwnwnwwsweenwnwnw\r\n"
			+ "wswweswwwswnwswswwwwseewswswwnw\r\n"
			+ "swswwwwnwswsweswwswwswswswswswwsww\r\n"
			+ "neseneneeweneneeneneneneneneneeswnenw\r\n"
			+ "nwswswswswswswswswswsesw\r\n"
			+ "nwnwnenwnwnwswnwnwesenwnwnwnwswnwnwswnenw\r\n"
			+ "neswwswseswseneswswseswswswswswswseswswse\r\n"
			+ "swseswsweswswseseswseseswswswswswswnwsw\r\n"
			+ "swsweswesweneeneswneenwneneneneene\r\n"
			+ "seseseseseswsenwseseseseswsese\r\n"
			+ "swwswwewwnewwneswwswwwnewww\r\n"
			+ "neneeeneneeseenenweeee\r\n"
			+ "enwwwswnwnwnewswswwnwwweeseww\r\n"
			+ "nwneeenenenenesenweseeneeweneswsw\r\n"
			+ "nenenewneneneneneneneeenenenenenenene\r\n"
			+ "seeseseseseneseeeseswenwnweseswnwseee\r\n"
			+ "swneswseswseswsesenewsesenesewenesw\r\n"
			+ "nwnwwwwwnwwewwnwwwwwwsenwnw\r\n"
			+ "swswswwsewswswwswnwwsweswnwswwwww\r\n"
			+ "seswseseswseswneseseenwseswwsw\r\n"
			+ "newnewesewwswsewnw\r\n"
			+ "weseesesewneseseseesenesesesesesewse\r\n"
			+ "eeeeseeeeeneeeenenewe\r\n"
			+ "wswwseswseneswswswswnesweseswnwswswswe\r\n"
			+ "eweeseeeeeeneneeeseeseeseswese\r\n"
			+ "nweenwwnwnwwnwenwnwnwnwwwswwswnwse\r\n"
			+ "wwwnenwnwswwnwwwwwnwnwnwwnwwnenwse\r\n"
			+ "enenenenenewnwnesweseewenenesenee\r\n"
			+ "esewswnwenwwnwnwnwnwnwnwswnwwwwse\r\n"
			+ "eeweneswenenweswnesweneneeneee\r\n"
			+ "wseneeseeesewnwswswnenesewneswee\r\n"
			+ "swneeenenenenenwnenenenenenenenewnesene\r\n"
			+ "nwswswswwswseswswswswnwswswsewwswswsw\r\n"
			+ "neenwnwnwnenwnwnwnwnwnwnenwnww\r\n"
			+ "eeeswenweeeseeeeeseseeeee\r\n"
			+ "swseswswswseseswseswneseseswswswsese\r\n"
			+ "nenwnenenwnwnenenenwswnenwnenwnewnwenene\r\n"
			+ "nwnwseseseswswseswswseswsweswswswseseseswse\r\n"
			+ "swnewwwwswwwwsww\r\n"
			+ "seseseswsweswswswswseswseseswseswswsww\r\n"
			+ "nwnenewnenwnwnenwnwnenenenenenesenenenwnw\r\n"
			+ "nwenwnenwnwsenenesenwnwswnesenwneew\r\n"
			+ "swwswwseswswswswwenenenwsene\r\n"
			+ "swswsweseseseseswseswswseswnwswnwseswsenw\r\n"
			+ "wsesenewnewnwwwnwsewnwsewwwnwww\r\n"
			+ "sewswnenesewswenwsenwswneseeneseswsw\r\n"
			+ "swwnwswnwwnwsenwnwwwnwwenenwwnwew\r\n"
			+ "seseseeseswneeseseswesesesesenwswwne\r\n"
			+ "neneeeesenenwnenweeneswneeweseeene\r\n"
			+ "swwswswswsewwwswwnenweweewsewnwsw\r\n"
			+ "seeswseswseseseswseseseseseseswwswnese\r\n"
			+ "esweseseseseseeseseseeeseseseeseenw\r\n"
			+ "wswwswwswswswnwswswswswew\r\n"
			+ "nwnwnwnwnwnwnwnwnweenwwnwnenwsenwnwswnw\r\n"
			+ "eeenwnenewneeeneeeneeeeswneneee\r\n"
			+ "nwwnwwnesesweswswswwseseneeswsweewsw\r\n"
			+ "neweneneeneneneweneneesenenenenenene\r\n"
			+ "nwwnweswnwnwswewnwewenwewwwnw\r\n"
			+ "seswsesesenwwseswneseseseeseseswse\r\n"
			+ "wnwwwnwwwwnwwwewwwwwwww\r\n"
			+ "enwseseseseswswsesesesesesesesesesesesesw\r\n"
			+ "wnwnwwnwnwewnwwwnwwnwwwswnw\r\n"
			+ "eseswseenwneenewnwnwesewnweswswsw\r\n"
			+ "neseswwswwwswswneeswwnweswseswnwesw\r\n"
			+ "seeseseseeenweseeseseeseeswseeese\r\n"
			+ "nwseeseseweseseseeseseeesewnwsesese\r\n"
			+ "swswswseswnwseseswswnwnwsweswseseswswsesw\r\n"
			+ "wweenwswwnwwwwnwnwnwwnwswnwwne\r\n"
			+ "senwwwnewwwwwwswwnwwnwsewwese\r\n"
			+ "swswswswswswswswwswneswswswswseswswswsw\r\n"
			+ "wswswswswswwswwwsweewwswswswswswsw\r\n"
			+ "neneneneeewseneneneneneeneneneeswne\r\n"
			+ "swswneswnwswswswswseswnwswswswwswesese\r\n"
			+ "swwwwwwswsewwwwwenewwwww\r\n"
			+ "nwswwwnwswnewsenwesenwenwnwnwesene\r\n"
			+ "nenewwwnenwweswswwwseswswswwew\r\n"
			+ "seneseseseswswseseswsesesew\r\n"
			+ "swsenwweswwwnewnwnwenwseswsenwswesene\r\n"
			+ "nwnwnwnwnwnenenwnwnwnwnwnenwnenwnwnwnwse\r\n"
			+ "swswswnwswswseswswsweswsweseswswswsewsw\r\n"
			+ "wwswseseseseswweswseeswseswneswswse\r\n"
			+ "sweseswswsesewseseseswsenwneswnesesesese\r\n"
			+ "nenenwnwnwnesenenwnwnenwnweswnenwnwnene\r\n"
			+ "eseneesesenesewsw\r\n"
			+ "seseseseswswsesesesesesewnwseseswswsenwne\r\n"
			+ "sweenenenweeneenene\r\n"
			+ "swwesweneenweeeeese\r\n"
			+ "wseseswseeseseswseneseswnwswseswwswseswse\r\n"
			+ "nwseseeseseeseeseeeenwewwewe\r\n"
			+ "wswswwseswswwswswswswwwwswnenwwsewsw\r\n"
			+ "seswswnwswseswswswswneswnewsweswnwnwse\r\n"
			+ "seswseseseswseseswsenwswsesesesesesesesw\r\n"
			+ "nwsenenwseenwnenenwneswnwnwnww\r\n"
			+ "nenwwwnwnwwwsewwwnwwnwwnwnwswnwne\r\n"
			+ "eeeenesweenenweeene\r\n"
			+ "eseweswseeesesesesenwwnenwsweeeese\r\n"
			+ "ewwwwwwwsewwwwwwnwnwnwnwww\r\n"
			+ "seswseseswseesesesesesewneswseseswswsw\r\n"
			+ "wwwwwwnwwwwwewwwwswwweww\r\n"
			+ "newnewsewswsewenwnwwswneswewesw\r\n"
			+ "eneswneswsweeenwswnwswnwnwswswswsw\r\n"
			+ "sesenweseeseeeeeeee\r\n"
			+ "swswenwnwnwwenwwswsewneewseeswsesw\r\n"
			+ "wneswwwnewwneswwseswwswswwwnew\r\n"
			+ "swwwswwsewseseneswnwneswwwwnwww\r\n"
			+ "eswnwswnwneenwneneseneseswneneneenenenw\r\n"
			+ "senewnwnwsweneswseeneseeewewswneswe\r\n"
			+ "swnwseseenewnenwnewne\r\n"
			+ "neneneneseeneeneneewneneneee\r\n"
			+ "nwnwnwwnwnwwwwnwnwwnwnwwnwwsenww\r\n"
			+ "swwneeneswswseswwsweneswwsenwswswswsw\r\n"
			+ "neneswnwnenewenwnenenwsenwnenenwnwnw\r\n"
			+ "seeeseseeeseeeesewsesee\r\n"
			+ "wsewswwsewwswnesewwnwwwenenenwnw\r\n"
			+ "esenweseeeseeeeseeeseeesweenw\r\n"
			+ "enweeseseenwsesenweseseswnwse\r\n"
			+ "eewneewwneeneeneneneseneneenene\r\n"
			+ "sesesenwneswwswswneseseseswswswseseswsene\r\n"
			+ "weesenewnwswswswnweswswseswsww\r\n"
			+ "seseseeseswseeseseseseseseswsenenwseese\r\n"
			+ "nwwewswwsenwnwsenwnenwnwnwenenwsenesw\r\n"
			+ "seeeeswenweseeeswneswswenwswnwwne\r\n"
			+ "swswsenwseenwsenwnenesewnwwene\r\n"
			+ "eeeeeenweeeeeneseseeeewsese\r\n"
			+ "nwenwwnwnwnwwnwswwwnwwnwwnwnwwwe\r\n"
			+ "nwsenenesenwneeneswnwwnwneseewsewse\r\n"
			+ "swwsenwnenwnwnwsenewsenwsenenwewnenese\r\n"
			+ "wwwwewwnwwwwwwww\r\n"
			+ "neneneneneeneenwneneeneneneeneswnene\r\n"
			+ "senesesesesesesenesesesesesesesesewsesw\r\n"
			+ "enwwswwnewswenewsewsenew\r\n"
			+ "nenesenwwseneneseneneneswnenenenewwnenenw\r\n"
			+ "eeeeeeeesweeeeeseeeeneenw\r\n"
			+ "nwnwwnewesewswsweewwwswneesene\r\n"
			+ "seseeseseeeseewseseeseseseneseee\r\n"
			+ "swenwnenwnwsesewswswnwnwenwnwnwnwene\r\n"
			+ "weswnwswswseseeeswneeeswenweenene\r\n"
			+ "swseswseswsenwseseseseswswswseswsesesw\r\n"
			+ "eseneeswnwswwneseene\r\n"
			+ "swswnwswswswswnwwwswnesesewswwsesw\r\n"
			+ "eeweewsenweeeeeesenwsee\r\n"
			+ "seneseswswweswswsesewseesw\r\n"
			+ "weeeeseeseseeeeseeeseseseee\r\n"
			+ "nwsenwswnwnwnwnwewnwwnwnwnwnwwnwwew\r\n"
			+ "enwnwswseseeneswseswswnwseswswsese\r\n"
			+ "neswnwnenenwnenwnenenenenwnenenenenenenw\r\n"
			+ "wswswswswnwswswswswswswsweswswswswwwsw\r\n"
			+ "eeeneneeeeeneeesweweeeene\r\n"
			+ "eneswnwneweneesw\r\n"
			+ "wwwesewswswwwswwnwwwwwswwsw\r\n"
			+ "nwnwnwswnenwnwnenwnenenesenwnwnwnwnwnwnw\r\n"
			+ "eeseeweswseeeeeeeseesenesenw\r\n"
			+ "eweeeneneeeneeseeeeneeeneene\r\n"
			+ "neseseseneseesenwsewsenwseseseseswsesesw\r\n"
			+ "swswenwnewnwwwsenwweewsenwweww\r\n"
			+ "neswswswswswswwswswswnwswswswswsweneswswsw";
	
	@Override
	public String part1() {
		//just like 2017 day 11, huge shoutout to redblobgames for their simple(ish) breakdown of hexagonal coordinates
		//https://www.redblobgames.com/grids/hexagons/
		
		//as mentioned in link, hexagonal coordinates can be stored in a three-variable format
		//so, keep a list of coord3s representing flipped tiles - if not in the list, is unflipped
		HashSet<Coord3> flipped = new HashSet<Coord3>();
		
		for(String line : input.split("\r\n")) {
			Coord3 pos = new Coord3(0,0,0);
			for(int i = 0; i < line.length(); i++) {
				char c = line.charAt(i);
				String move = c + "";
				//grab second half of move if necessary, and update index
				if(c == 'n' || c == 's') {
					i++;
					move += line.charAt(i);
				}
				
				switch(move) {
				case "e":
					pos.sumSelf(new Coord3(1,0,-1));
					break;
				case "w":
					pos.sumSelf(new Coord3(-1,0,1));
					break;
				case "ne":
					pos.sumSelf(new Coord3(1,-1,0));
					break;
				case "nw":
					pos.sumSelf(new Coord3(0,-1,1));
					break;
				case "se":
					pos.sumSelf(new Coord3(0,1,-1));
					break;
				case "sw":
					pos.sumSelf(new Coord3(-1,1,0));
					break;
				}
			}
			
			//try to add to flipped - if already present, "unflip" by removing
			if(!flipped.add(pos)) {
				flipped.remove(pos);
			}
		}
		
		return Integer.toString(flipped.size());
	}

	@Override
	public String part2() {
		HashSet<Coord3> flipped = new HashSet<Coord3>();
		
		for(String line : input.split("\r\n")) {
			Coord3 pos = new Coord3(0,0,0);
			for(int i = 0; i < line.length(); i++) {
				char c = line.charAt(i);
				String move = c + "";
				//grab second half of move if necessary, and update index
				if(c == 'n' || c == 's') {
					i++;
					move += line.charAt(i);
				}
				
				switch(move) {
				case "e":
					pos.sumSelf(new Coord3(1,0,-1));
					break;
				case "w":
					pos.sumSelf(new Coord3(-1,0,1));
					break;
				case "ne":
					pos.sumSelf(new Coord3(1,-1,0));
					break;
				case "nw":
					pos.sumSelf(new Coord3(0,-1,1));
					break;
				case "se":
					pos.sumSelf(new Coord3(0,1,-1));
					break;
				case "sw":
					pos.sumSelf(new Coord3(-1,1,0));
					break;
				}
			}
			
			//try to add to flipped - if already present, "unflip" by removing
			if(!flipped.add(pos)) {
				flipped.remove(pos);
			}
		}
		
		for(int day = 0; day < 100; day++) {
			HashSet<Coord3> newFlipped = new HashSet<Coord3>();
			
			//only white tiles that can flip are neighbors of black, so build list of unique neighbors
			HashSet<Coord3> uniqueUnflipped = new HashSet<Coord3>();
			
			//on pass through black tiles, both calculate new state and put neighbors in unique
			for(Coord3 cur : flipped) {
				//retain cur in new if 1 or 2 flipped neighbors
				int neighbor = 0;
				for(Coord3 c : hexAdjacent(cur)) {
					if(flipped.contains(c))
						neighbor++;
					else
						//if not in flipped, is a white tile that needs to be added to neighbors list
						uniqueUnflipped.add(c);
				}
				if(neighbor == 1 || neighbor == 2)
					newFlipped.add(cur);
			}
			
			for(Coord3 cur : uniqueUnflipped) {
				//flip if exactly 2 adjacent
				int neighbor = 0;
				for(Coord3 c : hexAdjacent(cur))
					if(flipped.contains(c))
						neighbor++;
				if(neighbor == 2)
					newFlipped.add(cur);
			}
			
			flipped = newFlipped;
		}
		
		return Integer.toString(flipped.size());
	}
	
	public ArrayList<Coord3> hexAdjacent(Coord3 middle) {
		ArrayList<Coord3> _return = new ArrayList<Coord3>();
		_return.add(middle.sum(new Coord3(0,-1,1)));
		_return.add(middle.sum(new Coord3(1,-1,0)));
		_return.add(middle.sum(new Coord3(1,0,-1)));
		_return.add(middle.sum(new Coord3(0,1,-1)));
		_return.add(middle.sum(new Coord3(-1,1,0)));
		_return.add(middle.sum(new Coord3(-1,0,1)));
		return _return;
	}

	public static void main(String[] args) {
		DayRunner.run(new Day24());
	}

}

package advent.aoc2023;

import advent.utilities.general.*;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;

public class Day24 implements IDay {

    static String input;

    public static void main(String[] args) {
        input = Input.fetchInput(2023, 24);
        DayRunner.run(new Day24());
    }

    @Override
    public String part1() {
        LinkedList<Pair<Coord3L, Coord3L>> hailstones = new LinkedList<Pair<Coord3L, Coord3L>>();
        for(String s : input.split("\n")) {
            String[] chunks = s.split(" @ ");
            String[] posNums = chunks[0].split(", ");
            Coord3L pos = new Coord3L(Long.parseLong(posNums[0].trim()), Long.parseLong(posNums[1].trim()), Long.parseLong(posNums[2].trim()));
            String[] velNums = chunks[1].split(", ");
            Coord3L vel = new Coord3L(Long.parseLong(velNums[0].trim()), Long.parseLong(velNums[1].trim()), Long.parseLong(velNums[2].trim()));
            hailstones.add(new Pair<>(pos,vel));
        }
        //any two values of t for x0a+vxa*t = x0b+vxb*t ?
        long testLow = 200000000000000L;
        long testHigh = 400000000000000L;
        int count = 0;
        //for a = ((x0a, y0a), (vxa, vya))
        //and b = ((x0b, y0b), (vxb, vyb))
        //and using t1 and t2 for variables
        //we have vxa * t1 + -vxb * t2 = (x0b - x0a)
        //and vya * t1 + -vyb * t2 = (y0b - y0a)
        // [a1 b1] = [vxa -vxb]
        // [a2 b2] = [vya -vyb]
        //by cramers rule:
        //t1 = (-(x0b - x0a) * vyb + vxb * (y0b - y0a))/(-vxa*vyb + vya*vxb)
        //t2 = ((vxa * (x0b - x0a) - (y0b - y0a) * vya)/(-vxa * vyb + vya * vxb)

        for(int indexA = 0; indexA < hailstones.size(); indexA++) {
            for(int indexB = indexA + 1; indexB < hailstones.size(); indexB++) {
                Pair<Coord3L, Coord3L> hailA = hailstones.get(indexA);
                Pair<Coord3L, Coord3L> hailB = hailstones.get(indexB);

                Coord3L posA = hailA.key;
                Coord3L velA = hailA.value;
                Coord3L posB = hailB.key;
                Coord3L velB = hailB.value;
                double determinant = ((velB.x * velA.y) - (velA.x * velB.y));
                double c1 = posB.x-posA.x;
                double c2 = posB.y - posA.y;

                double t1 = (velB.x*c2 - velB.y * c1)/determinant;
                double t2 = (velA.x * c2 - velA.y *c1)/determinant;
                if(t1 < 0 || t2 < 0)
                    continue;

                double xInt = posA.x + t1 * velA.x;
                if(xInt < testLow || xInt > testHigh)
                    continue;
                double yInt = posB.y + t2 * velB.y;
                if(yInt < testLow || yInt > testHigh)
                    continue;

                count++;
            }

        }
        return Integer.toString(count);
    }

    @Override
    public String part2() {
        LinkedList<Pair<Coord3L, Coord3L>> hailstones = new LinkedList<>();
        String[] lines = input.split("\n");
        for(int i = 0; i < 4; i++) {
            if(i==2)
                continue;
            String s = lines[i];
            String[] chunks = s.split(" @ ");
            String[] posNums = chunks[0].split(", ");
            Coord3L pos = new Coord3L(Long.parseLong(posNums[0].trim()), Long.parseLong(posNums[1].trim()), Long.parseLong(posNums[2].trim()));
            String[] velNums = chunks[1].split(", ");
            Coord3L vel = new Coord3L(Long.parseLong(velNums[0].trim()), Long.parseLong(velNums[1].trim()), Long.parseLong(velNums[2].trim()));
            hailstones.add(new Pair<>(pos,vel));
        }

        //find x0, y0, z0, vx, vy, vz so that
        // x0 + tvx = x0n + t*vxn
        // y0 + tvy = y0n + t*vyn
        // z0 + tvz = z0n + t*vzn
        //for all n hailstones
        //we start off with 6 unknowns, and every hailstone adds 3 eqns and 1 unknown
        //so examining just 3 hailstones, we get a system with 9 equations and 9 unknowns - solvable (theoretically)
        Coord3L pos1 = hailstones.get(0).key;
        Coord3L vel1 = hailstones.get(0).value;
        Coord3L pos2 = hailstones.get(1).key;
        Coord3L vel2 = hailstones.get(1).value;
        Coord3L pos3 = hailstones.get(2).key;
        Coord3L vel3 = hailstones.get(2).value;



        //equation:
        //rockPos + t * rockVel = hailPos + t * hailVel
        //or rockPos = hailPos + (hailVel - rockVel) * t
        //so it's like we're modifying each hailstone's velocity by the negative of rock velocity
        //to make the positions line up properly at t
        //assuming solution exists:
        //we should be able to find an x, y, z plane w/ forms similar to Cramer's
        //then extrapolate original position from those points?
        //orthogonals from each combination
        BigInteger[] orth1 = vecScaleBig(pos1, vel1, pos2, vel2);
        BigInteger[] orth2 = vecScaleBig(pos1, vel1, pos3, vel3);
        BigInteger[] orth3 = vecScaleBig(pos2, vel2, pos3, vel3);

        //System.out.println(orth1 + " " + orth2 + " " + orth3);

        /*
        Coord3L velX = cross(orth2.key, orth3.key);
        //remember - reverse order (j negative)
        Coord3L velY = cross(orth3.key, orth1.key);
        Coord3L velZ = cross(orth1.key, orth2.key);
         */

        BigInteger[] velX = bigCross(orth2, orth3);
        //backwards - j is negative
        BigInteger[] velY = bigCross(orth3, orth1);
        BigInteger[] velZ = bigCross(orth1, orth2);

        /*
        //find velocity value at intersect using cross product (ish)
        Coord3L rockVel = new Coord3L((velX.x * orth1.value + velY.x * orth2.value + velZ.x * orth3.value),(velX.y * orth1.value + velY.y * orth2.value + velZ.y * orth3.value),(velX.z * orth1.value + velY.z * orth2.value + velZ.z * orth3.value));
        double time = dot(orth1.key, cross(orth2.key, orth3.key));
        //divide by time, and round since we know it must be an integer
        //begging and pleading to the floating point gods
        rockVel = new Coord3L(Math.round(rockVel.x/time), Math.round(rockVel.y/time), Math.round(rockVel.z/time));
         */

        BigInteger[] rockVel = bigLinComb(velX, orth1[3], velY, orth2[3], velZ, orth3[3]);
        BigInteger time = bigDot(orth1, bigCross(orth2, orth3));
        rockVel[0] = rockVel[0].divide(time);
        rockVel[1] = rockVel[1].divide(time);
        rockVel[2] = rockVel[2].divide(time);

        //velocity differences
        //we only need 2 points from here on?
        /*
        Coord3L vDiff1 = vecSub(vel1, rockVel);
        Coord3L vDiff2 = vecSub(vel2, rockVel);
        Coord3L vDiffProd = cross(vDiff1, vDiff2);
         */

        BigInteger[] vDiff1 = bigSub(intoBig(vel1), rockVel);
        BigInteger[] vDiff2 = bigSub(intoBig(vel2), rockVel);
        BigInteger[] vDiffProd = bigCross(vDiff1, vDiff2);

        /*
        //now, trace vel back to find initial point based on collisions with 2 different hailstones
        long xScale = dot(vDiffProd, cross(pos2, vDiff2));
        long yScale = dot(vDiffProd, cross(pos1, vDiff1));
        //one side cancels to 0 when substituting constants into Cramer's?
        long zScale = dot(pos1, vDiffProd);
        long dividingFactor = dot(vDiffProd, vDiffProd);
         */

        BigInteger xScale = bigDot(vDiffProd, bigCross(intoBig(pos2), vDiff2));
        BigInteger yScale = bigDot(vDiffProd, bigCross(intoBig(pos1), vDiff1));
        BigInteger zScale = bigDot(intoBig(pos1), vDiffProd);
        BigInteger dividingFactor = bigDot(vDiffProd, vDiffProd);

        /*
        Coord3L rock = new Coord3L((xScale*vDiff1.x - yScale * vDiff2.x + zScale * vDiffProd.x), (xScale*vDiff1.y - yScale * vDiff2.y + zScale * vDiffProd.y), (xScale*vDiff1.z - yScale * vDiff2.z + zScale * vDiffProd.z));
        System.out.println(rock.x/dividingFactor + " " + rock.y/dividingFactor + " " + rock.z/dividingFactor);
        return Long.toString(rock.x/dividingFactor + rock.y/dividingFactor + rock.z/dividingFactor);
         */
        BigInteger[] rock = bigLinComb(vDiff1, xScale, vDiff2, yScale.negate(), vDiffProd, zScale);
        rock[0] = rock[0].divide(dividingFactor);
        rock[1] = rock[1].divide(dividingFactor);
        rock[2] = rock[2].divide(dividingFactor);

        return rock[0].add(rock[1]).add(rock[2]).toString();
    }


    BigInteger[] intoBig(Coord3L c) {
        return new BigInteger[] {BigInteger.valueOf(c.x), BigInteger.valueOf(c.y), BigInteger.valueOf(c.z)};
    }

    //first 3 are coord, last is scalar
    BigInteger[] vecScaleBig(Coord3L pos1, Coord3L vel1, Coord3L pos2, Coord3L vel2) {
        Pair<Coord3L, Long> ans = vecAndScale(pos1, vel1, pos2, vel2);
        return new BigInteger[] {BigInteger.valueOf(ans.key.x), BigInteger.valueOf(ans.key.y), BigInteger.valueOf(ans.key.z), BigInteger.valueOf(ans.value)};
    }

    BigInteger[] bigSub(BigInteger[] a, BigInteger[] b) {
        return new BigInteger[] {a[0].subtract(b[0]), a[1].subtract(b[1]), a[2].subtract(b[2])};
    }


    //i j k
    //a0 a1 a2
    //b0 b1 b2
    BigInteger[] bigCross(BigInteger[] a, BigInteger[] b) {
        BigInteger i = a[1].multiply(b[2]).subtract(b[1].multiply(a[2]));
        BigInteger j = b[0].multiply(a[2]).subtract(a[0].multiply(b[2]));
        BigInteger k = a[0].multiply(b[1]).subtract(a[1].multiply(b[0]));
        return new BigInteger[]{i,j,k};
    }

    BigInteger bigDot(BigInteger[] a, BigInteger[] b) {
        return a[0].multiply(b[0]).add(a[1].multiply(b[1])).add(a[2].multiply(b[2]));
    }

    //linear combination of three 3-item BigInteger vectors w/ scalars
    BigInteger[] bigLinComb(BigInteger[] x, BigInteger scaleX, BigInteger[] y, BigInteger scaleY, BigInteger[] z, BigInteger scaleZ) {
        BigInteger i = x[0].multiply(scaleX).add(y[0].multiply(scaleY)).add(z[0].multiply(scaleZ));
        BigInteger j = x[1].multiply(scaleX).add(y[1].multiply(scaleY)).add(z[1].multiply(scaleZ));
        BigInteger k = x[2].multiply(scaleX).add(y[2].multiply(scaleY)).add(z[2].multiply(scaleZ));
        return new BigInteger[] {i,j,k};
    }

    //orthogonal and scalar (plane?)
    Pair<Coord3L, Long> vecAndScale(Coord3L pos1, Coord3L vel1, Coord3L pos2, Coord3L vel2) {
        Coord3L posDiff = vecSub(pos1, pos2);
        Coord3L velDiff = vecSub(vel1, vel2);
        Coord3L velCross = cross(vel1, vel2);
        return new Pair<>(cross(posDiff, velDiff), dot(posDiff,velCross));
    }

    Coord3L vecSub(Coord3L a, Coord3L b) {
        return new Coord3L(a.x-b.x, a.y-b.y, a.z-b.z);
    }

    //classic dot product
    long dot(Coord3L a, Coord3L b) {
        return a.x*b.x + a.y*b.y + a.z*b.z;
    }

    //classic cross prod
    //i  j  k
    //ax ay az
    //bx by bz
    Coord3L cross(Coord3L a, Coord3L b) {
        return new Coord3L((a.y * b.z - a.z * b.y), (a.z*b.x - a.x * b.z), (a.x*b.y - b.x*a.y));
    }
}

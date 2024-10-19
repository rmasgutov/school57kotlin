{
  inputs = {
    nixpkgs.url      = "github:NixOS/nixpkgs/nixos-unstable";
    flake-utils.url  = "github:numtide/flake-utils";
  };

  outputs = { nixpkgs, flake-utils, ... }:
    flake-utils.lib.eachDefaultSystem (system:
      let
        pkgs = import nixpkgs {
          inherit system;
        };

        jre = pkgs.zulu17;
        kotlin = pkgs.kotlin.override { inherit jre; };
      in
      {
        devShells.default = with pkgs; mkShell {
          buildInputs = [ jre kotlin ];
          shellHook = ''
            alias kt="${kotlin} \$1 --unclude-runtime -d result.jar && java -jar result.jar && rm result.jar"
            export JAVA_HOME="${jre}"
          '';
        };
      }
    );
}
